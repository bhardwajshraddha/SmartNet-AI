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
        hover:border-sky-500
        transition
      "
    >
      <h3 className="text-slate-400 text-sm font-medium">{title}</h3>

      <h2 className="text-4xl font-bold text-white mt-3">{value}</h2>
    </div>
  );
}

export default SummaryCard;
