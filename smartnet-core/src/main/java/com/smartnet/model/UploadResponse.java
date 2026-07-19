package com.smartnet.model;

public class UploadResponse {

    private String fileName;
    private AnalysisResult analysisResult;

    public UploadResponse() {
    }

    public UploadResponse(String fileName, AnalysisResult analysisResult) {
        this.fileName = fileName;
        this.analysisResult = analysisResult;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public AnalysisResult getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(AnalysisResult analysisResult) {
        this.analysisResult = analysisResult;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "fileName='" + fileName + '\'' +
                ", analysisResult=" + analysisResult +
                '}';
    }
}