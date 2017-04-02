package kecskesk.javaeightplugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import kecskesk.javaeightplugin.modifications.PackageCreator;
import kecskesk.javaeightplugin.traversal.ProjectTraverser;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		ProjectTraverser traverser = new ProjectTraverser();
		traverser.traverse(workspace);
		
		PackageCreator packageCreator = new PackageCreator();
		try {
			packageCreator.createPackage(workspace);
		} catch (java.util.concurrent.ExecutionException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
