const graphElo = document.getElementById("graphElo").getContext("2d");

let myChart = new Chart (graph, {
type = bar,
data: {
labels : ["Elo"],
datasets: [
{
labels: "Evolution de l'Elo",
data: [800, 1200, 1500, 1300, 1200, 1500],
backgroundColor: "blue",

}
]
}