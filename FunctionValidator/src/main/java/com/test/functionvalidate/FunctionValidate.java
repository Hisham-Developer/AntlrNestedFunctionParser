package com.test.functionvalidate;

import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.functionvalidate.validate.MyFunctionsLexer;
import com.functionvalidate.validate.MyFunctionsParser;
import com.functionvalidate.validate.MyFunctionsParser.ArgumentContext;
import com.functionvalidate.validate.MyFunctionsParser.FunctionContext;
import com.functionvalidate.validate.MyFunctionsParser.FunctionsContext;

public class FunctionValidate {


	public static void main(String[] args) {
		String input = "FUNCTION.concat(FUNCTION.substring(S,Integer,Integer),String)";
		ANTLRInputStream str = new ANTLRInputStream(input);
		MyFunctionsLexer lexer = new MyFunctionsLexer(str);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MyFunctionsParser parser = new MyFunctionsParser(tokens);
		parser.removeErrorListeners(); // remove ConsoleErrorListener 
		parser.addErrorListener(new VerboseListener()); // add ours
		FunctionsContext tree = parser.functions();
		FunctionValidateVisitorImpl visitor = new FunctionValidateVisitorImpl();
		visitor.visit(tree);
		System.out.println("-->"+tree.toStringTree( parser ));
		AST ast=new AST(tree);
		System.out.println( "Improved ParseTree:\n" + ast.toString() );
		
		JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),tree);
        viewr.setScale(1.5);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
	}


}
