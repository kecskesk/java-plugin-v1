package kecskesk.quickfix;

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

import kecskesk.quickfix.engine.NullCheckRefactorEngine;

@SuppressWarnings("restriction")
public class MyQuickAssistProcessor implements IQuickAssistProcessor {

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
				for (IProblemLocation problem : locations) {
					if (context instanceof AssistContext) {
						ICompilationUnit cu = context.getCompilationUnit();
						ASTNode selectedNode = problem.getCoveredNode(context.getASTRoot());
					}
				}
			}
		} };
	}

}
