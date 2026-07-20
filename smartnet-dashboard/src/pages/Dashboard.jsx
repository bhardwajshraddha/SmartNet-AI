import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import DashboardCards from "../components/DashboardCards";
function Dashboard() {
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
          <DashboardCards />
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
