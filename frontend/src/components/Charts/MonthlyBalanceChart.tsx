"use client";

import React from "react";
import ReactApexChart from "react-apexcharts";

interface Props {
  months: string[];
  entries: number[];
  expenses: number[];
}

export default function MonthlyBalanceChart({
  months,
  entries,
  expenses,
}: Props) {
  const series = [
    {
      name: "Entradas",
      data: entries,
    },
    {
      name: "Saídas",
      data: expenses,
    },
  ];

  const options: ApexCharts.ApexOptions = {
    chart: {
      type: "bar",
      toolbar: { show: false },
    },
    plotOptions: {
      bar: {
        horizontal: false,
        columnWidth: "55%",
        borderRadius: 4,
      },
    },
    dataLabels: {
      enabled: false,
    },
    stroke: {
      show: true,
      width: 2,
      colors: ["transparent"],
    },
    xaxis: {
      categories: months,
      labels: {
        style: {
          colors: "#fff",
        },
      },
    },
    yaxis: {
      title: {
        text: "R$",
        style: {
          color: "#fff",
        },
      },
      labels: {
        style: {
          colors: "#fff",
        },
      },
    },
    fill: {
      opacity: 1,
    },
    tooltip: {
      y: {
        formatter: (val: number) => `R$ ${val.toFixed(2)}`,
      },
      theme: "dark",
    },
    legend: {
      labels: {
        colors: "#fff",
      },
    },
    colors: ["#22c55e", "#ef4444"],
  };
  return (
    <div className="bg-gray-700 rounded-xl p-4 shadow">
      <h2 className="text-lg font-semibold mb-4">Entradas e Gastos por Mês</h2>
      <ReactApexChart
        options={options}
        series={series}
        type="bar"
        width="100%"
        height="100%"
      />
    </div>
  );
}
