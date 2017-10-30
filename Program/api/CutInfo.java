import java.util.regex.*;

class CutInfo{
  private String result;

  public CutInfo(String result){
    this.result = result;
  }

  public String cutRate(){
      String regex = "\\d+";
      // System.out.print(result);
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(result);
  		if (matcher.find()) {
  			return matcher.group(0);
  		} else {
  			throw new IllegalStateException("No match found.");
  		}
  }


}
