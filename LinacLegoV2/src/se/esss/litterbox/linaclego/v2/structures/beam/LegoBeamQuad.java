package se.esss.litterbox.linaclego.v2.structures.beam;

import se.esss.litterbox.linaclego.v2.Lego;
import se.esss.litterbox.linaclego.v2.LinacLegoException;
import se.esss.litterbox.linaclego.v2.structures.LegoSlot;
import se.esss.litterbox.simplexml.SimpleXmlReader;

public class LegoBeamQuad extends LegoBeam
{
	private static final long serialVersionUID = -4796356135331798183L;
	private double lengthmm;
	private double gradient;
	@SuppressWarnings("unused")
	private double radius;

	public LegoBeamQuad() throws LinacLegoException 
	{
		super();
	}
	public LegoBeamQuad(LegoSlot legoSlot, int beamListIndex, SimpleXmlReader beamTag) throws LinacLegoException 
	{
		super(legoSlot, beamListIndex, beamTag);
	}
	public LegoBeamQuad(LegoSlot legoSlot, int beamListIndex, String id, String disc, String model) throws LinacLegoException
	{
		super(legoSlot, beamListIndex, id, disc, model);
	}
	@Override
	protected double[] getLocalTranslationVector() throws LinacLegoException 
	{
		double[] localInputVec = {0.0, 0.0, 0.0};
		localInputVec[2] = lengthmm * 0.001;
		return localInputVec;
	}
	@Override
	protected double[][] getLocalRotationMatrix() throws LinacLegoException 
	{
		double[][] localRotMat = { {1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}};
		return localRotMat;
	}
	@Override
	public void addDataElements() throws LinacLegoException 
	{
		addDataElement("l", "0.0", "double","mm");
		addDataElement("g", "0.0", "double","T/m");
		addDataElement("r", "0.0", "double","mm");
	}
	@Override
	protected String defaultLatticeCommand() throws LinacLegoException 
	{
		String latticeCommand = "";
		latticeCommand = getDefaultLatticeFileKeyWord();
		latticeCommand = latticeCommand + Lego.space + getDataValue("l");
		latticeCommand = latticeCommand + Lego.space + getDataValue("g");
		latticeCommand = latticeCommand + Lego.space + getDataValue("r");
		return latticeCommand;
	}
	@Override
	protected String latticeCommand(String latticeType) throws LinacLegoException 
	{
		if (latticeType.equalsIgnoreCase("tracewin")) return defaultLatticeCommand();
		return defaultLatticeCommand();
	}
	@Override
	protected double reportEnergyChange() throws LinacLegoException {return 0;}
	@Override
	protected double reportSynchronousPhaseDegrees() throws LinacLegoException {return 0;}
	@Override
	protected double reportQuadGradientTpm() throws LinacLegoException {return gradient;}
	@Override
	protected double reportDipoleBendDegrees() throws LinacLegoException {return 0;}
	@Override
	protected void calcParameters() throws LinacLegoException 
	{
		lengthmm = Double.parseDouble(getDataValue("l"));
		gradient = Double.parseDouble(getDataValue("g"));
		radius = Double.parseDouble(getDataValue("r"));
	}
	@Override
	protected void setType() {type = "quad";}
	@Override
	public String getDefaultLatticeFileKeyWord() {return "QUAD";}
	@Override
	public String getLatticeFileKeyWord(String latticeType) 
	{
		if (latticeType.equalsIgnoreCase("tracewin")) return  getDefaultLatticeFileKeyWord();
		return getDefaultLatticeFileKeyWord();
	}
	@Override
	public void addLatticeData(String latticeType, String[] sdata) 
	{
		if (latticeType.equalsIgnoreCase("tracewin"))
		{
			setDataValue("l", sdata[0]);
			setDataValue("g", sdata[1]);
			setDataValue("r", sdata[2]);
		}
		
	}
	@Override
	public String getPreferredIdLabelHeader() 
	{
		String labelHeader = "QUA-";
		try {
			double gradient = Double.parseDouble(getDataValue("g"));
			if (gradient >= 0) labelHeader = "QH-";
			if (gradient <  0) labelHeader = "QV-";
		} catch (NumberFormatException | LinacLegoException e) {}
		return labelHeader;
	}

	@Override
	public String getPreferredDiscipline() {return "BMD";}
	@Override
	public double characteristicValue() {return Math.abs(gradient);}
	@Override
	public String characteristicValueUnit() {return "T/m";}

}
