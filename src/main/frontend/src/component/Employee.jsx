import React, { Component } from "react";
import ListEmployeesComponent from "./ListEmployeesComponent";

class Employee extends Component {
  render() {
    return (
      <>
        <h1>Employee Application</h1>
        <ListEmployeesComponent />
      </>
    );
  }
}

export default Employee;
