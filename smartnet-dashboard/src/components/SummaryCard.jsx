function SummaryCard({ title, value }) {
  return (
    <div
      className="
        bg-slate-900
        border
        border-slate-700
        rounded-2xl
        p-6
        shadow-lg
        hover:shadow-sky-500/20
        hover:border-sky-500
        hover:-translate-y-1
        transition-all
        duration-300"
    >
      <h3 className="text-slate-400 text-sm font-medium">{title}</h3>

      <h2 className="text-3xl font-bold text-white mt-3">{value}</h2>
    </div>
  );
}

export default SummaryCard;
