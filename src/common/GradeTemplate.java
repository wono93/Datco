package common;

public class GradeTemplate {

	public String userGrade(int point) {
		
		String fileName = "";
		
		if(point>100000) fileName = "Diamond";
		else if(point>50000) fileName ="Emerald";
		else if(point>10000) fileName = "Lapis_Lazuli";
		else if(point>5000) fileName = "Ruby";
		else if(point>1000) fileName = "Gold";
		else if(point>500) fileName = "Iron";
		else fileName = "Coal";
		return (fileName+".png");
	}
	
}
