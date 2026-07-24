import {
  LayoutDashboard,
  Upload,
  BarChart3,
  Network,
  ShieldAlert,
  Settings,
} from "lucide-react";

import { NavLink } from "react-router-dom";

function Sidebar() {
  const menus = [
    {
      icon: LayoutDashboard,
      label: "Dashboard",
      path: "/",
    },
    {
      icon: Upload,
      label: "Upload PCAP",
      path: "/",
    },
    {
      icon: BarChart3,
      label: "Statistics",
      path: "/statistics",
    },
    {
      icon: Network,
      label: "Flows",
      path: "/flows",
    },
    {
      icon: ShieldAlert,
      label: "Threats",
      path: "/threats",
    },
    {
      icon: Settings,
      label: "Settings",
      path: "/settings",
    },
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
          <NavLink
            key={menu.label}
            to={menu.path}
            className={({ isActive }) =>
              `w-full flex items-center gap-3 px-4 py-3 mb-2 rounded-xl transition ${
                isActive
                  ? "bg-cyan-500/20 text-cyan-400"
                  : "hover:bg-cyan-500/10 hover:text-cyan-400"
              }`
            }
          >
            <menu.icon size={20} />
            <span>{menu.label}</span>
          </NavLink>
        ))}
      </nav>
    </aside>
  );
}

export default Sidebar;
