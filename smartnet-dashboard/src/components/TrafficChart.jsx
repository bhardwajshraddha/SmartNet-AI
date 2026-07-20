import {
  LineChart,
  Line,
  ResponsiveContainer,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
} from "recharts";

const data = [
  { time: "10:00", packets: 12 },
  { time: "10:05", packets: 25 },
  { time: "10:10", packets: 20 },
  { time: "10:15", packets: 38 },
  { time: "10:20", packets: 42 },
  { time: "10:25", packets: 35 },
];

function TrafficChart() {
  return (
    <div className="bg-slate-900 rounded-2xl border border-slate-800 p-6 h-[350px]">
      <h2 className="text-xl text-white font-semibold mb-5">
        Traffic Overview
      </h2>

      <ResponsiveContainer width="100%" height="90%">
        <LineChart data={data}>
          <CartesianGrid stroke="#1e293b" />
          <XAxis dataKey="time" stroke="#94a3b8" />
          <YAxis stroke="#94a3b8" />
          <Tooltip />
          <Line
            type="monotone"
            dataKey="packets"
            stroke="#06b6d4"
            strokeWidth={3}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}

export default TrafficChart;
