package com.test.functionvalidate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import com.functionvalidate.validate.MyFunctionsParser;
import com.functionvalidate.validate.MyFunctionsParser.ArgumentContext;
import com.functionvalidate.validate.MyFunctionsParser.FunctionContext;
import com.functionvalidate.validate.MyFunctionsParserBaseVisitor;

public class FunctionValidateVisitorImpl extends MyFunctionsParserBaseVisitor<String> {

	Map<String, String> map = new HashMap<String, String>();
	Map<String, String> mapdata = new HashMap<String, String>();

	public FunctionValidateVisitorImpl()
	{
		map.put("FUNCTION.add", "String:Integer|String,Integer");
		map.put("FUNCTION.concat", "String:String");
		map.put("FUNCTION.mul", "String:Integer,Integer");
		map.put("FUNCTION.test", "String:");
		map.put("FUNCTION.substring", "Boolean:String,Integer,Integer,String,Integer,Integer");
		/*map.put("FUNCTION.test", "String:");*/
		//map.put("FUNCTION.substring", "String:String,Integer,Integer,String,Integer,Integer");
		
		mapdata.put("input.ENV.MAC","String");
	}




	@Override
	public String visitFunctions(MyFunctionsParser.FunctionsContext ctx) {
		for (int i = 0; i < ctx.getChildCount(); ++i)
		{
			ParseTree c = ctx.getChild(i);
			if (c.getText().equals("<EOF>"))
				continue;
			String top_level_result = visit(ctx.getChild(i));
			if (top_level_result == null)
			{
				//System.err.println("Failed semantic analysis :: "+ ctx.getChild(i).getText());
			}
		}
		return null;
	}

	@Override
	public String visitFunction( MyFunctionsParser.FunctionContext ctx) {
		System.err.println("--------------------------visitFunction--------------------------------");
		String name = ctx.getChild(2).getText();
		String type=map.get("FUNCTION." + name);
		if (type == null)
		{
			throw new FunctionInvalidException("Invalid function :: "+"FUNCTION name--->"+name);
		}
		
		if(type.split(":").length==1){
			return type.split(":")[0];
		}
		else{
		String result_type = type.split(":")[0];
		String args_types = type.split(":")[1];
		String[] expected_arg_type = args_types.split(",");
		int j = 4;
		ParseTree a = ctx.getChild(j);
		if (a instanceof MyFunctionsParser.FunctionContext)
		{
			if(ctx.getChildCount()!=6){
				throw new FunctionInvalidException("Function."+name+" :: is having argument mismatch :: "+"Expected Argument is "+Arrays.asList(args_types));
			}
			else{
				String v = visit(a);
				if (!v.equals(result_type))
				{
					throw new FunctionInvalidException("Function."+name+" :: type not found");
				}
			}
		} else {
			for (int i = j; i < ctx.getChildCount(); i += 2)
			{
				if(ctx.getChildCount()<6 || (((ctx.getChildCount()-4)/2)!=expected_arg_type.length)){
					throw new FunctionInvalidException("Function."+name+" :: is having argument mismatch :: "+"Expected Argument is "+Arrays.asList(args_types));
				}
				else{
					ParseTree parameter = ctx.getChild(i);
					String v = visit(parameter);
					if(expected_arg_type[(i - j)/2].equals("Any")){
						System.err.println("if----");
					}
					else{
						if(expected_arg_type[(i - j)/2].contains("|")){
							System.err.println("---------------------contains |----------------------------");
							String[] splitttedexpectedArgument = expected_arg_type[(i - j)/2].split("\\|");
							Integer status=0;
							for (String string : splitttedexpectedArgument) {
								if(v.equals(string)){
									status=1;
								}
							}
							if(status!=1){
								throw new FunctionInvalidException("Function."+name+" ::is having parameter of invalid type,Expected is type is "+expected_arg_type[(i - j)/2]);
							}
						}
						else if(v.equals(expected_arg_type[(i - j)/2])){
							System.err.println("else else if----");
						}
						else{
							throw new FunctionInvalidException("Function."+name+" ::is having parameter of invalid type,Expected is type is "+expected_arg_type[(i - j)/2]);
						}
					}
					
				}
			}
		}
		return result_type;
		}
	}


	@Override
	public String visitArgument(ArgumentContext ctx){
		//System.out.println("-------------->ArgumentContext"+ctx.getText());
		ParseTree c = ctx.getChild(0);
		if (c instanceof TerminalNodeImpl)
		{
			System.out.println("55555555555555555555555555555"+c.getText());
			return c.getText();
		}
		else{
			String ret=visit(c);
			System.out.println("66666666666666666666666666666"+ret);
			return ret;
		}
	}












}
