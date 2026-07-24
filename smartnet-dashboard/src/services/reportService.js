import api from "./api";

export const downloadJsonReport = async () => {
  const response = await api.get("/analysis-report", {
    responseType: "blob",
  });

  const url = window.URL.createObjectURL(response.data);

  const link = document.createElement("a");

  link.href = url;
  link.download = "smartnet-report.json";

  document.body.appendChild(link);

  link.click();

  link.remove();

  window.URL.revokeObjectURL(url);
};

export const downloadCsvReport = async () => {
  const response = await api.get("/analysis-report/csv", {
    responseType: "blob",
  });

  const url = window.URL.createObjectURL(response.data);

  const link = document.createElement("a");

  link.href = url;
  link.download = "smartnet-report.csv";

  document.body.appendChild(link);

  link.click();

  link.remove();

  window.URL.revokeObjectURL(url);
};
