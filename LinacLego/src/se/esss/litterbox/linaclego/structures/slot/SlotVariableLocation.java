/*
Copyright (c) 2014 European Spallation Source

This file is part of LinacLego.
LinacLego is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 2 
of the License, or any newer version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with this program. 
If not, see https://www.gnu.org/licenses/gpl-2.0.txt
*/
package se.esss.litterbox.linaclego.structures.slot;

public class SlotVariableLocation 
{
	private int nble;
	private String dataId;
	private SlotVariable slotVariable;
	
	public int getNble() {return nble;}
	public String getDataId() {return dataId;}
	public SlotVariable getSlotVariable() {return slotVariable;}

	public SlotVariableLocation(int nble, String dataId, SlotVariable slotVariable)
	{
		this.nble = nble;
		this.dataId = dataId;
		this.slotVariable = slotVariable;
	}

}
