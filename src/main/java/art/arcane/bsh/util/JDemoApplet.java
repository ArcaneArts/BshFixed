/*****************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one                *
 * or more contributor license agreements.  See the NOTICE file              *
 * distributed with this work for additional information                     *
 * regarding copyright ownership.  The ASF licenses this file                *
 * to you under the Apache License, Version 2.0 (the                         *
 * "License"); you may not use this file except in compliance                *
 * with the License.  You may obtain a copy of the License at                *
 *                                                                           *
 *     http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing,                *
 * software distributed under the License is distributed on an               *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                    *
 * KIND, either express or implied.  See the License for the                 *
 * specific language governing permissions and limitations                   *
 * under the License.                                                        *
 *                                                                           *
 *                                                                           *
 * This file is part of the BeanShell Java Scripting distribution.           *
 * Documentation and updates may be found at http://www.beanshell.org/       *
 * Patrick Niemeyer (pat@pat.net)                                            *
 * Author of Learning Java, O'Reilly & Associates                            *
 *                                                                           *
 *****************************************************************************/


package art.arcane.bsh.util;

import javax.swing.*;
import java.awt.*;

import art.arcane.bsh.EvalError;
import art.arcane.bsh.Interpreter;
import art.arcane.bsh.TargetError;
import bsh.*;
import bsh.util.*;

/**
	Run bsh as an applet for demo purposes.
*/
public class JDemoApplet extends JApplet
{
	public void init()
	{
		String debug = getParameter("debug");
		if ( debug != null && debug.equals("true") )
			Interpreter.DEBUG=true;

		String type = getParameter("type");
		if ( type != null && type.equals("desktop") )
			// start the desktop
			try {
				new Interpreter().eval( "desktop()" );
			} catch ( TargetError te ) {
				te.printStackTrace();
				System.out.println( te.getTarget() );
				te.getTarget().printStackTrace();
			} catch ( EvalError evalError ) {
				System.out.println( evalError );
				evalError.printStackTrace();
			}
		else
		{
			getContentPane().setLayout(new BorderLayout());
			JConsole console = new JConsole();
			getContentPane().add("Center", console);
			Interpreter interpreter = new Interpreter( console );
			new Thread(interpreter).start();
		}
	}
}

