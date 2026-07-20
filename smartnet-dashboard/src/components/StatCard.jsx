function StatCard({ title, value, color }) {
  return (
    <div className="bg-slate-900 rounded-2xl border border-slate-800 p-6 shadow-lg hover:border-cyan-500 transition">
      <p className="text-slate-400 text-sm">{title}</p>

      <h2 className={`text-4xl font-bold mt-3 ${color}`}>{value}</h2>
    </div>
  );
}

export default StatCard;
