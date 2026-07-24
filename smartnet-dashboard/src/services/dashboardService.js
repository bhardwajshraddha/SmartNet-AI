import api from "./api";

export const getDashboardSummary = async () => {
  const response = await api.get("/dashboard-summary");
  return response.data;
};

export const getProtocolSummary = async () => {
  const response = await api.get("/protocol-summary");
  return response.data;
};

export const getFlows = async () => {
  const response = await api.get("/flows");
  return response.data;
};
