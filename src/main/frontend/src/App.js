import React, { Component } from "react";
import "./App.css";
import Employee from "./component/Employee";

class App extends Component {
  render() {
    return (
      <div className="container">
        <Employee />
      </div>
    );
  }
}

export default App;
