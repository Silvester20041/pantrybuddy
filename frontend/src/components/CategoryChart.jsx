import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend
} from "recharts";

function CategoryChart({ data }) {

  const chartData = [
    { name: "Vegetables", value: data.vegetableItems },
    { name: "Dairy", value: data.dairyItems },
    { name: "Grains", value: data.grainItems },
    { name: "Fruits", value: data.fruitItems },
    { name: "Protein", value: data.proteinItems }
  ];

  const COLORS = [
    "#4CAF50",
    "#2196F3",
    "#FFC107",
    "#F44336",
    "#9C27B0"
  ];

  return (
    <PieChart width={500} height={350}>
      <Pie
        data={chartData}
        cx="50%"
        cy="50%"
        outerRadius={120}
        dataKey="value"
        label
      >
        {chartData.map((entry, index) => (
          <Cell
            key={index}
            fill={COLORS[index % COLORS.length]}
          />
        ))}
      </Pie>

      <Tooltip />

      <Legend />
    </PieChart>
  );
}

export default CategoryChart;