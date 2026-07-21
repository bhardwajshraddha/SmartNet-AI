import { useEffect, useState } from "react";

import {
  PieChart,
  Pie,
  Tooltip,
  Cell,
  ResponsiveContainer,
  Legend,
} from "recharts";

import { getProtocolSummary } from "../services/dashboardService";

const COLORS = ["#0ea5e9", "#22c55e", "#f59e0b", "#ef4444", "#8b5cf6"];

function ProtocolChart() {
  const [data, setData] = useState([]);

  useEffect(() => {
    loadProtocols();
  }, []);

  const loadProtocols = async () => {
    try {
      const response = await getProtocolSummary();
      setData(response);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="bg-slate-900 rounded-2xl border border-slate-700 p-6 shadow-lg">
      <h2 className="text-xl font-bold text-white mb-6">
        Protocol Distribution
      </h2>

      <div className="h-80">
        <ResponsiveContainer width="100%" height="100%">
          <PieChart>
            <Pie
              data={data}
              dataKey="packets"
              nameKey="protocol"
              outerRadius={110}
              label
            >
              {data.map((entry, index) => (
                <Cell
                  key={entry.protocol}
                  fill={COLORS[index % COLORS.length]}
                />
              ))}
            </Pie>

            <Tooltip />

            <Legend />
          </PieChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}

export default ProtocolChart;
