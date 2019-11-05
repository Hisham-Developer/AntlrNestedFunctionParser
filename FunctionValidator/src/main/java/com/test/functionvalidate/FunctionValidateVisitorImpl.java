package com.test.functionvalidate;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import com.functionvalidate.validate.MyFunctionsParser;
import com.functionvalidate.validate.MyFunctionsParser.ArgumentContext;
import com.functionvalidate.validate.MyFunctionsParser.FunctionContext;
import com.functionvalidate.validate.MyFunctionsParserBaseVisitor;

public class FunctionValidateVisitorImpl extends MyFunctionsParserBaseVisitor<String> {
	
	Map<String, String> map = new HashMap<String, String>();

    public FunctionValidateVisitorImpl()
    {
        map.put("FUNCTION.add", "Integer:Integer,Integer");
        map.put("FUNCTION.concat", "String:String,String");
        map.put("FUNCTION.mul", "Integer:Integer,Integer");
        map.put("FUNCTION.substring", "String:String,Integer,Integer");
    }
	
	@Override
	public String visitFunctions(MyFunctionsParser.FunctionsContext ctx) {
		System.out.println("testtt");
		System.out.println("count-->"+ctx.getChildCount());
		for (int i = 0; i < ctx.getChildCount(); ++i)
        {
			ParseTree c = ctx.getChild(i);
			
            if (c.getText() == "<EOF>")
                continue;
            String top_level_result = visit(ctx.getChild(i));
            System.out.println("top_level_result-->"+top_level_result);
            if (top_level_result == null)
            {
                System.out.println("Failed semantic analysis: "+ ctx.getChild(i).getText());
            }
        }
        return null;
	}
	
	@Override
	public String visitFunction( MyFunctionsParser.FunctionContext ctx) {
		// Get function name and expected type information.
		System.out.println("-------------->visitFunction");
        String name = ctx.getChild(2).getText();
        String type=map.get("FUNCTION." + name);
        if (type == null)
        {
            return null; // not declared in function table.
        }
        String result_type = type.split(":")[0];
        String args_types = type.split(":")[1];
        String[] expected_arg_type = args_types.split(",");
        int j = 4;
        ParseTree a = ctx.getChild(j);
        if (a instanceof MyFunctionsParser.FunctionContext)
        {
        	String v = visit(a);
            if (v != result_type)
            {
                return null; // Handle type mismatch.
            }
        } else {
            for (int i = j; i < ctx.getChildCount(); i += 2)
            {
            	ParseTree parameter = ctx.getChild(i);
                String v = visit(parameter);
                if (v != expected_arg_type[(i - j)/2])
                {
                    return null; // Handle type mismatch.
                }
            }
        }
        return result_type;
	}
	
	
	@Override
	public String visitArgument(ArgumentContext ctx){
		System.out.println("-------------->ArgumentContext");
		ParseTree c = ctx.getChild(0);
        if (c instanceof TerminalNodeImpl)
        {
            // Unclear if what this is supposed to parse:
            // Mutate "1" to "Integer"?
            // Mutate "Integer" to "String"?
            // Or what?
            return c.getText();
        }
        else
            return visit(c);
	}
	

}
