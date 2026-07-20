import { Search, Bell, UserCircle2 } from "lucide-react";

function Topbar() {
  return (
    <header className="h-20 bg-slate-900 border-b border-slate-800 flex items-center justify-between px-8">
      <div>
        <h2 className="text-2xl font-bold text-white">SmartNet</h2>

        <p className="text-sm text-slate-400">
          Intelligent Network Traffic Analysis & Threat Detection Platform
        </p>
      </div>

      <div className="flex items-center gap-5">
        <div className="flex items-center bg-slate-800 rounded-xl px-4 py-2">
          <Search className="text-slate-400" size={18} />

          <input
            placeholder="Search..."
            className="bg-transparent outline-none text-white ml-3"
          />
        </div>

        <Bell className="text-white cursor-pointer" />

        <UserCircle2 size={34} className="text-cyan-400 cursor-pointer" />
      </div>
    </header>
  );
}

export default Topbar;
