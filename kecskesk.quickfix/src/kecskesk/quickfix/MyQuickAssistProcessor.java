package kecskesk.quickfix;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.internal.ui.text.correction.AssistContext;
import org.eclipse.jdt.internal.ui.text.java.AbstractJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.IProblemLocation;
import org.eclipse.jdt.ui.text.java.IQuickAssistProcessor;
import org.eclipse.jface.viewers.StyledString;

@SuppressWarnings("restriction")
public class MyQuickAssistProcessor implements IQuickAssistProcessor {

	private static final String REGEX_NULL = "([a-z][a-zA-Z0-9]*) *== ?null";
	private static final String REGEX_NOT_NULL = "([a-z][a-zA-Z0-9]*) *!= ?null";

	@Override
	public boolean hasAssists(IInvocationContext context) throws CoreException {
		ASTNode coveredNode = context.getCoveredNode();
		return coveredNode.getNodeType() == ASTNode.TYPE_DECLARATION;
	}

	@Override
	public IJavaCompletionProposal[] getAssists(IInvocationContext context, IProblemLocation[] locations)
			throws CoreException {
		return new IJavaCompletionProposal[] { new AbstractJavaCompletionProposal() {
			@Override
			public org.eclipse.jface.viewers.StyledString getStyledDisplayString() {
				ICompilationUnit compilationUnit = context.getCompilationUnit();
				return new StyledString("Generate Optional from null check in type "
						+ compilationUnit.findPrimaryType().getElementName());
			}

			@Override
			protected int getPatternMatchRule(String pattern, String string) {
				return -1;
			};

			@Override
			public void apply(org.eclipse.jface.text.ITextViewer viewer, char trigger, int stateMask, int offset) {
				if (context instanceof AssistContext) {
					AssistContext assistContext = (AssistContext) context;
					try {
						IType type = assistContext.getCompilationUnit().findPrimaryType();
						IMethod[] methods = type.getMethods();

						for (IMethod method : methods) {
							System.out.println("Found a method: ");
							String source = method.getSource();
							if ((source.contains("==") || source.contains("!=")) && source.contains("null")) {
								refactorNullChecks(type, method, source);
							}
						}

					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				}
			}
		} };
	}

	private void refactorNullChecks(IType type, IMethod method, String source) throws JavaModelException {
		String modifiedSource = source;

		Set<String> variablesToRefactor = new HashSet<>();

		Pattern notNullPattern = Pattern.compile(REGEX_NOT_NULL);
		Pattern nullPattern = Pattern.compile(REGEX_NULL);

		Matcher notNullMatcher = notNullPattern.matcher(source);
		Matcher nullMatcher = nullPattern.matcher(source);

		while (notNullMatcher.find()) {
			System.out.println("Found value nnm: " + notNullMatcher.group(1));
			variablesToRefactor.add(notNullMatcher.group(1));
		}

		while (nullMatcher.find()) {
			System.out.println("Found value nm: " + nullMatcher.group(1));
			variablesToRefactor.add(nullMatcher.group(1));
		}

		
		System.out.println(variablesToRefactor.size());
		
		
		/*modifiedSource = modifiedSource.replaceAll(REGEX_NOT_NULL, "$1.isPresent()");
		modifiedSource = modifiedSource.replaceAll(REGEX_NULL, "!$1.isPresent()");

		method.delete(true, null);
		type.createMethod(modifiedSource, null, true, null);*/
	}

}
