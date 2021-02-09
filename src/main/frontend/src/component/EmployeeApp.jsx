import React, { Component } from "react";

import ListEmployeesComponent from "./ListEmployeesComponent";

import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import EmployeeComponent from "./EmployeeComponent";

class EmployeeApp extends Component {
  render() {
    return (
      <Router>
        <>
          <h1>Employee Application</h1>

          <Switch>
            <Route path="EMPLOYEE" exact component={ListEmployeesComponent} />

            <Route path=":id" component={EmployeeComponent} />
          </Switch>
        </>
      </Router>
    );
  }
}
export default EmployeeApp;
