import DataTable from "react-data-table-component";
import React, { Component } from "react";

const data = [
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1983" },
  { id: 1, title: "Conan the Barbarian", year: "1984" },
  { id: 1, title: "Conan the Barbarian", year: "1985" },
  { id: 1, title: "Conan the Barbarian", year: "1986" },
  { id: 1, title: "Conan the Barbarian", year: "1987" },
  { id: 1, title: "Conan the Barbarian", year: "1988" },
  { id: 1, title: "Conan the Barbarian", year: "1989" },
  { id: 1, title: "Conan the Barbarian", year: "1981" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" },
  { id: 1, title: "Conan the Barbarian", year: "1982" }
];
const columns = [
  {
    name: "Title",
    selector: "title",
    sortable: true
  },
  {
    name: "Year",
    selector: "year",
    sortable: true,
    right: true
  }
];
const handleChange = state => {
  // You can use setState or dispatch with something like Redux so we can use the retrieved data
  console.log("Selected Rows: ", state.selectedRows);
};
export default class DT extends Component {
  render() {
    return <DataTable title="Arnold Movies" columns={columns} data={data} />;
  }
}
