function ExportButtons({ summary, flows }) {
  const exportJSON = () => {
    const report = {
      generatedAt: new Date().toLocaleString(),
      summary,
      flows,
    };

    const blob = new Blob([JSON.stringify(report, null, 2)], {
      type: "application/json",
    });

    const url = URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = url;
    link.download = "smartnet-report.json";

    link.click();

    URL.revokeObjectURL(url);
  };

  const exportCSV = () => {
    let csv = "Source IP,Destination IP,Protocol,Packets,Bytes\n";

    flows.forEach((flow) => {
      csv += `${flow.flowKey.sourceIp},${flow.flowKey.destinationIp},${flow.flowKey.protocol},${flow.packetCount},${flow.totalBytes}\n`;
    });

    const blob = new Blob([csv], {
      type: "text/csv",
    });

    const url = URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = url;
    link.download = "smartnet-report.csv";

    link.click();

    URL.revokeObjectURL(url);
  };

  return (
    <div className="flex gap-4 mt-8 mb-8">
      <button
        onClick={exportJSON}
        className="bg-emerald-600 hover:bg-emerald-700 text-white px-6 py-3 rounded-xl font-semibold transition"
      >
        Export JSON
      </button>

      <button
        onClick={exportCSV}
        className="bg-sky-600 hover:bg-sky-700 text-white px-6 py-3 rounded-xl font-semibold transition"
      >
        Export CSV
      </button>
    </div>
  );
}

export default ExportButtons;
