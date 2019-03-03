package developers.hub.com.thelearningapp.Helper;

public class CodingQuestionDataModel {

    String qName;
    String qDesc;
    int solvedBy;
    String inputDesc;
    String OutputDesc;
    String constraints;
    String sampleInput;
    String sampleOutput;

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public String getqDesc() {
        return qDesc;
    }

    public void setqDesc(String qDesc) {
        this.qDesc = qDesc;
    }

    public int getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(int solvedBy) {
        this.solvedBy = solvedBy;
    }

    public String getInputDesc() {
        return inputDesc;
    }

    public void setInputDesc(String inputDesc) {
        this.inputDesc = inputDesc;
    }

    public String getOutputDesc() {
        return OutputDesc;
    }

    public void setOutputDesc(String outputDesc) {
        OutputDesc = outputDesc;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public CodingQuestionDataModel(String qName, String qDesc, int solvedBy, String inputDesc, String outputDesc, String constraints, String sampleInput, String sampleOutput) {
        this.qName = qName;
        this.qDesc = qDesc;
        this.solvedBy = solvedBy;
        this.inputDesc = inputDesc;
        OutputDesc = outputDesc;
        this.constraints = constraints;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
    }
}