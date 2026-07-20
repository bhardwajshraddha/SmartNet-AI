import {
  LayoutDashboard,
  Upload,
  BarChart3,
  Network,
  ShieldAlert,
  Settings,
} from "lucide-react";

function Sidebar() {
  const menus = [
    { icon: LayoutDashboard, label: "Dashboard" },
    { icon: Upload, label: "Upload PCAP" },
    { icon: BarChart3, label: "Statistics" },
    { icon: Network, label: "Flows" },
    { icon: ShieldAlert, label: "Threats" },
    { icon: Settings, label: "Settings" },
  ];

  return (
    <aside className="w-64 bg-slate-950 border-r border-slate-800 text-white flex flex-col">
      <div className="p-6 border-b border-slate-800">
        <h1 className="text-2xl font-bold text-cyan-400">SmartNet</h1>

        <p className="text-xs text-slate-400 mt-2">
          Intelligent Network Traffic Analysis
        </p>
      </div>

      <nav className="flex-1 p-4">
        {menus.map((menu) => (
          <button
            key={menu.label}
            className="w-full flex items-center gap-3 px-4 py-3 mb-2 rounded-xl hover:bg-cyan-500/10 hover:text-cyan-400 transition"
          >
            <menu.icon size={20} />
            {menu.label}
          </button>
        ))}
      </nav>
    </aside>
  );
}

export default Sidebar;
