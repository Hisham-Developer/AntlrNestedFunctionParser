package com.test.functionvalidate;


/**
 * @author VIKRAM A S
 */

import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.test.functionvalidate.VerboseListener;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import com.functionvalidate.validate.MyFunctionsLexer;
import com.functionvalidate.validate.MyFunctionsParser;
import com.functionvalidate.validate.MyFunctionsParser.FunctionsContext;


public class FunctionValidate {


	public static void main(String[] args) {
		try {
			String input = "Functions.substring(String,Integer,Integer,String,Integer,Integer)";
			//String input = "FUNCTION.test()";
			ANTLRInputStream str = new ANTLRInputStream(input);
			MyFunctionsLexer lexer = new MyFunctionsLexer(str);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			MyFunctionsParser parser = new MyFunctionsParser(tokens);
			parser.removeErrorListeners(); // remove ConsoleErrorListener 
			parser.addErrorListener(new VerboseListener());
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
			frame.setSize(500,500);
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/*public static void main(String[] args) {

				System.out.println("Functions.substring(Functions.test(1,Functions.test(\"TestString1\")),0.9,0,\"TestString2\",true)".replaceAll("[+-]?\\d+(\\.\\d+)?", "Decimal"));
	}*/


}
