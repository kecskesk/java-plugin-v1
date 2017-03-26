package kecskesk.javaeightplugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		IProject[] projects = root.getProjects();
		// Loop over all projects
		for (IProject project : projects) {
			try {
				printProjectInfo(project);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void printProjectInfo(IProject project) throws CoreException, JavaModelException {
		System.out.println("Working in project " + project.getName());
		// check if we have a Java project
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			IJavaProject javaProject = JavaCore.create(project);
			printPackageInfos(javaProject);
		}
	}

	private void printPackageInfos(IJavaProject javaProject) throws JavaModelException {
		IPackageFragment[] packages = javaProject.getPackageFragments();
		for (IPackageFragment mypackage : packages) {
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				if (mypackage.getElementName().isEmpty()) {
					System.out.println("\nDefault Package");
				} else {
					System.out.println("\nPackage " + mypackage.getElementName());
					printICompilationUnitInfo(mypackage);
				}

			}

		}
	}

	private void printICompilationUnitInfo(IPackageFragment mypackage) throws JavaModelException {
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			printCompilationUnitDetails(unit);
		}
	}

	private void printCompilationUnitDetails(ICompilationUnit unit) throws JavaModelException {
		System.out.println("  - Source file " + unit.getElementName());
		Document doc = new Document(unit.getSource());

		System.out.println("    Has number of lines: " + doc.getNumberOfLines());
		printIMethods(unit);
	}

	private void printIMethods(ICompilationUnit unit) throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
		for (IType type : allTypes) {
			printIMethodDetails(type);
		}
	}

	private void printIMethodDetails(IType type) throws JavaModelException {

		if (type.isClass()) {
			System.out.print("      Class ");
		} else if (type.isEnum()) {
			System.out.print("      Enum ");
		} else if (type.isAnnotation()) {
			System.out.print("      Annotation ");
		} else if (type.isInterface()) {
			System.out.print("      Interface ");
		} else {
			System.out.print("      ");
		}

		System.out.println(type.getElementName());
		
		IField[] fields = type.getFields();
		for (IField field : fields) {

			System.out.println("      - Field name " + field.getElementName());
			System.out.println("        Modifiers " + translageFlags(field.getFlags()));

		}

		IMethod[] methods = type.getMethods();
		for (IMethod method : methods) {

			System.out.println("      - Method name " + method.getElementName());
			System.out.println("        Signature " + method.getSignature());
			System.out.println("        Modifiers " + translageFlags(method.getFlags()));
			System.out.println("        Return Type " + method.getReturnType());

		}
	}
	
	private String translageFlags(int flag) {
		String flagsFound = "";
		if (Flags.isPrivate(flag)) flagsFound += "private ";
		if (Flags.isProtected(flag)) flagsFound += "protected ";
		if (Flags.isPublic(flag)) flagsFound += "public ";
		if (Flags.isPackageDefault(flag)) flagsFound += "package private ";
		if (Flags.isStatic(flag)) flagsFound += "static ";
		if (Flags.isFinal(flag)) flagsFound += "final ";
				
		return flagsFound;
	}
}
