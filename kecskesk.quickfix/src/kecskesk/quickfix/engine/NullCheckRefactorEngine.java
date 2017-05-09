package kecskesk.quickfix.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

public class NullCheckRefactorEngine {

	public NullCheckRefactorEngine(IType type, IMethod method, String source) {
		this.type = type;
		this.method = method;
		this.source = source;
	}

	private IType type;
	private IMethod method;
	private String source;

	private static final String REGEX_NULL = "([a-z][_a-zA-Z0-9]*) *== ?null";
	private static final String REGEX_NOT_NULL = "([a-z][_a-zA-Z0-9]*) *!= ?null";

	public void refactor() throws JavaModelException {
		String modifiedSource = source;

		Pattern notNullPattern = Pattern.compile(REGEX_NOT_NULL);
		Pattern nullPattern = Pattern.compile(REGEX_NULL);

		Matcher notNullMatcher = notNullPattern.matcher(source);
		Matcher nullMatcher = nullPattern.matcher(source);

		Set<String> variablesToRefactor = findVariablesToRefactor(notNullMatcher, nullMatcher);
		Map<String, String> variablesWithTypes = new HashMap<>();
		
		modifiedSource = replaceNullChecks(modifiedSource);
		
		modifiedSource = changeTypesToOptional(variablesToRefactor, modifiedSource, variablesWithTypes);		
		
		modifiedSource = changeUsagesToOptionalGet(modifiedSource, variablesWithTypes);		
		
		modifiedSource = changeCallsToOptionalOf(modifiedSource, variablesWithTypes);
		
		updateMethodWithSource(modifiedSource);
		
		updateMethodCallsInType();		
	}
	
	private void updateMethodCallsInType() {
		try {
			for (IMethod checkedMethod : type.getMethods()) {
				String methodSource = checkedMethod.getSource();
				if (methodSource.contains(method.getElementName())) {
					String methodCall = method.getElementName() + "(\\((?<=\\().*(?=\\))\\))";
					Pattern methodCallPattern = Pattern.compile(methodCall);
					Matcher methodCallMatcher = methodCallPattern.matcher(methodSource);
					while (methodCallMatcher.find()) {
						Pattern signaturePattern = Pattern.compile("^.*(" + checkedMethod.getElementName() + "(.*)\\)) *\\{");
						Matcher signatureMatcher = signaturePattern.matcher(methodSource);
						if (signatureMatcher.find()) {
							String methodFullSignature = signatureMatcher.group(1);
							if (methodCallMatcher.group(0).equals(methodFullSignature)) {
								continue;
							}
						}
						
						methodSource = methodSource.replace(methodCallMatcher.group(0), method.getElementName() + "(Optional.of" + methodCallMatcher.group(1) + ")");
					}

					checkedMethod.delete(true, null);
					type.createMethod(methodSource, null, true, null);
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

	private String changeCallsToOptionalOf(String source, Map<String, String> variablesWithTypes) {
		String newSource = source;
		for (Entry<String, String> variableEntry: variablesWithTypes.entrySet()) {
			
			String equityRegex = variableEntry.getKey() + " *=(([^;]|\\n)*);";
			
			String optionalOfReplace = variableEntry.getKey() + " = Optional.of($1);";
			
			newSource = newSource.replaceAll(equityRegex, optionalOfReplace);
		}
		
		return newSource;
	}

	private String changeUsagesToOptionalGet(String source, Map<String, String> variablesWithTypes) {
		String newSource = source;
		for (Entry<String, String> variableEntry: variablesWithTypes.entrySet()) {
			
			String usageNotDeclarationRegex = "(?<!Optional<" + variableEntry.getValue() + "> )" + variableEntry.getKey()
			+ "(?!\\.isPresent\\(\\))(?![_a-zA-Z0-9])(?! *=)";
			
			String usageReplace = variableEntry.getKey() + ".get()";
			
			newSource = newSource.replaceAll(usageNotDeclarationRegex, usageReplace);
		}
		
		return newSource;
	}

	private String changeTypesToOptional(Set<String> variablesToRefactor, String source, Map<String, String> variablesWithTypes) {
		String newSource = source;
		for (String variable : variablesToRefactor) {
			Pattern typePattern = Pattern.compile("([A-Z][_a-zA-Z0-9]*) *" + variable + "(?![_a-zA-Z0-9])");

			Matcher typeMatcher = typePattern.matcher(source);
			while (typeMatcher.find()) {
				variablesWithTypes.put(variable, typeMatcher.group(1));
				newSource = newSource.replace(typeMatcher.group(0), "Optional<" + typeMatcher.group(1) + "> " + variable);
			}
		}

		return newSource;
	}

	private void updateMethodWithSource(String modifiedSource) throws JavaModelException {
		method.delete(true, null);
		type.createMethod(modifiedSource, null, true, null);
	}

	private String replaceNullChecks(String modifiedSource) {
		modifiedSource = modifiedSource.replaceAll(REGEX_NOT_NULL, "$1.isPresent()");
		return modifiedSource.replaceAll(REGEX_NULL, "!$1.isPresent()");
	}

	private Set<String> findVariablesToRefactor(Matcher notNullMatcher, Matcher nullMatcher) {
		Set<String> variablesToRefactor = new HashSet<>();
		while (notNullMatcher.find()) {
			variablesToRefactor.add(notNullMatcher.group(1));
		}

		while (nullMatcher.find()) {
			variablesToRefactor.add(nullMatcher.group(1));
		}
		return variablesToRefactor;
	}
}
