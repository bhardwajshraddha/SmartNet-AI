import ProtocolChart from "../components/ProtocolChart";
import { useEffect, useState } from "react";

import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import UploadCard from "../components/UploadCard";
import DashboardCards from "../components/DashboardCards";

import { getDashboardSummary } from "../services/dashboardService";

function Dashboard() {
  const [summary, setSummary] = useState(null);

  const loadDashboard = async () => {
    try {
      const data = await getDashboardSummary();
      setSummary(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadDashboard();
  }, []);

  return (
    <div className="flex min-h-screen bg-slate-950">
      <Sidebar />

      <div className="flex-1 flex flex-col">
        <Topbar />

        <main className="p-8">
          <div className="mb-8">
            <h1 className="text-4xl font-bold text-white">Network Dashboard</h1>

            <p className="text-slate-400 mt-2">
              Intelligent Network Traffic Analysis & Threat Detection Platform
            </p>
          </div>

          <UploadCard onUploadSuccess={loadDashboard} />

          <DashboardCards summary={summary} />
          <div className="mt-8">
            <ProtocolChart />
          </div>
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
