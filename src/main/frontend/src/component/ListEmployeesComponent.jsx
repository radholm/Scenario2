import React, { Component } from "react";
import EmployeeDataService from "../service/EmployeeDataService";

class ListEmployeesComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employees: [],
      message: null,
    };
    this.deleteEmployeeClicked = this.deleteEmployeeClicked.bind(this);

    this.updateEmployeeClicked = this.updateEmployeeClicked.bind(this);

    this.addEmployeeClicked = this.addEmployeeClicked.bind(this);

    this.refreshEmployees = this.refreshEmployees.bind(this);
  }

  componentDidMount() {
    this.refreshEmployees();
  }

  refreshEmployees() {
    EmployeeDataService.retrieveAllEmployees().then((response) => {
      console.log(response);
      this.setState({ employees: response.data });
    });
  }

  deleteEmployeeClicked(id) {
    EmployeeDataService.deleteEmployee(id).then((response) => {
      this.setState({ message: `Delete of employee ${id} successful` });
      console.log(response);
      this.refreshEmployees();
    });
  }

  addEmployeeClicked() {
    this.props.history.push(`-1`);
  }

  updateEmployeeClicked(id) {
    this.props.history.push(`/employees/${id}`);
  }

  render() {
    return (
      <div className="container">
        <h3>All Employees</h3>
        <div className="container">
          <table className="table">
            <thead>
              <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Rank</th>
                <th>Salary</th>
                <th>Update</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {this.state.employees.map((employee) => (
                <tr key={employee.id}>
                  <td>{employee.id}</td>
                  <td>{employee.firstName}</td>
                  <td>{employee.lastName}</td>
                  <td>{employee.rank}</td>
                  <td>{employee.salary}</td>
                  {employee.isCEO ? "CEO" : ""}
                  {employee.isManager ? "Manager" : "Employee"}
                  <td>
                    {" "}
                    <button
                      className="btn btn-warning"
                      onClick={() => this.updateEmployeeClicked(employee.id)}
                    >
                      Update
                    </button>
                  </td>
                  <td>
                    {" "}
                    <button
                      className="btn btn-danger"
                      onClick={() => this.deleteEmployeeClicked(employee.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="row">
            <button
              className="btn btn-success"
              onClick={this.addEmployeeClicked}
            >
              Add
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default ListEmployeesComponent;
