<script>
import { Bar } from "vue-chartjs";

export default {
  extends: Bar,
  mounted() {
    this.renderChart(
      {
        labels: ["2", "3", "4", "5", "6", "7", "8", "8","2", "3", "4", "5", "6", "7", "8", "8"],
        datasets: [
          {
            label: "Booked",
            backgroundColor: "#4285F4",
            data: [40, 30, 12, 39, 10, 40, 39, 20, 40, 30, 12, 39, 10, 40, 39, 35]
          },
          {
            label: "Canceled",
            backgroundColor: "#f87979",
            data: [40, 20, 12, 39, 10, 40, 39, 30, 40, 39, 20, 40, 30, 12, 39, 10]
          }
        ]
      },
      { responsive: true, maintainAspectRatio: false }
    );
  }
};
</script>