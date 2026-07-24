import { Routes, Route } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import Statistics from "./pages/Statistics";
import Flows from "./pages/Flows";

function ComingSoon({ title }) {
  return (
    <div className="flex items-center justify-center h-screen bg-slate-950 text-white">
      <div className="text-center">
        <h1 className="text-5xl font-bold">{title}</h1>

        <p className="text-slate-400 mt-4">
          This page will be implemented soon.
        </p>
      </div>
    </div>
  );
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />

      <Route path="/statistics" element={<Statistics />} />

      <Route path="/flows" element={<Flows />} />

      <Route path="/threats" element={<ComingSoon title="Threats" />} />

      <Route path="/settings" element={<ComingSoon title="Settings" />} />
    </Routes>
  );
}

export default App;
