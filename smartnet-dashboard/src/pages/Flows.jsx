import { useEffect, useState } from "react";

import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import FlowTable from "../components/FlowTable";

import { getFlows } from "../services/dashboardService";

function Flows() {
  const [flows, setFlows] = useState([]);

  const loadFlows = async () => {
    try {
      const data = await getFlows();
      setFlows(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadFlows();
  }, []);

  return (
    <div className="flex min-h-screen bg-slate-950">
      <Sidebar />

      <div className="flex-1 flex flex-col">
        <Topbar />

        <main className="p-8">
          <div className="mb-8">
            <h1 className="text-4xl font-bold text-white">Network Flows</h1>

            <p className="text-slate-400 mt-2">
              View all detected network communication flows.
            </p>
          </div>

          <FlowTable flows={flows} />
        </main>
      </div>
    </div>
  );
}

export default Flows;
