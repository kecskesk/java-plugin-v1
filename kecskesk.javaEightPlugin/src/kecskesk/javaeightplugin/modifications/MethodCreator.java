package kecskesk.javaeightplugin.modifications;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class MethodCreator {

	public void createMethod(ExecutionEvent event) {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage activePage = window.getActivePage();
	    ISelection selection = activePage.getSelection();
	    if (selection != null) {
	        System.out.println("Got selection");
	        if (selection instanceof IStructuredSelection) {
	            System.out.println("Got a structured selection");
	            if (selection instanceof ITreeSelection) {
	                TreeSelection treeSelection = (TreeSelection) selection;
	                TreePath[] treePaths = treeSelection.getPaths();
	                if (treePaths.length < 1) {
	                	System.out.println("No path on tree");
	                	return;
	                }
	                TreePath treePath = treePaths[0];

	                System.out.println("Last");
	                Object lastSegmentObj = treePath.getLastSegment();
	                Class currClass = lastSegmentObj.getClass();
	                while(currClass != null) {
	                    System.out.println("  Class=" + currClass.getName());
	                    Class[] interfaces = currClass.getInterfaces();
	                    for(Class interfacey : interfaces) {
	                        System.out.println("   I=" + interfacey.getName());
	                    }
	                    currClass = currClass.getSuperclass();
	                }
	                if(lastSegmentObj instanceof IAdaptable) {
	                    IFile file = (IFile) ((IAdaptable) lastSegmentObj).getAdapter(IFile.class);
	                    if(file != null) {
	                        System.out.println("File=" + file.getName());
	                        String path = file.getRawLocation().toOSString();
	                        System.out.println("path: " + path);
	                    }
	                }
	            }
	        }
	    }        
	}

	/*private void createPackageInProject(IProject project) throws JavaModelException {
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
	}*/

}
