package kecskesk.javaeightplugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import kecskesk.javaeightplugin.modifications.MethodCreator;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {		
		MethodCreator methodCreator = new MethodCreator();
		methodCreator.createMethod(event);
		
		return null;
	}
}
