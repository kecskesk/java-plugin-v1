package kecskesk.javaeightplugin.modifications;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class PackageCreator {

	public void createPackage(IWorkspace workspace) throws ExecutionException {
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		IProject[] projects = root.getProjects();
		// Loop over all projects
		for (IProject project : projects) {
			try {
				// only work on open projects with the Java nature
				if (project.isOpen() & project.isNatureEnabled(JavaCore.NATURE_ID)) {
					createPackageInProject(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	private void createPackageInProject(IProject project) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder folder = project.getFolder("src");
		// folder.create(true, true, null);
		String packageName = getRandomString(10);
		IPackageFragmentRoot srcFolder = javaProject.getPackageFragmentRoot(folder);
		srcFolder.createPackageFragment(packageName, true, null);
		System.out.println(packageName);
	}
	
	private String getRandomString(int length) {
		final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    final int N = alphabet.length();
		String result = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result += alphabet.charAt(random.nextInt(N));
		}
		return result;
	}

}
