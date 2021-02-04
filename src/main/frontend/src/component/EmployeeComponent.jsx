import React, { Component } from "react";

import { Formik, Form, Field, ErrorMessage } from "formik";

import EmployeeDataService from "../service/EmployeeDataService";

const INSTRUCTOR = "in28minutes";

class EmployeeComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,

      description: "",
      firstName: "",
      lastName: "",
      rank: "",
    };

    this.onSubmit = this.onSubmit.bind(this);

    this.validate = this.validate.bind(this);
  }

  componentDidMount() {
    console.log(this.state.id);

    if (this.state.id === -1) {
      return;
    }

    EmployeeDataService.retrieveEmployee(this.state.id).then((response) =>
      this.setState({
        description: response.data.description,
        firstName: response.data.firstName,
        lastName: response.data.lastName,
        rank: response.data.rank,
      })
    );
  }

  validate(values) {
    let errors = {};

    if (!values.description) {
      errors.description = "Enter a Description";
    } else if (values.description.length < 5) {
      errors.description = "Enter atleast 5 Characters in Description";
    }

    return errors;
  }

  onSubmit(values) {
    let username = INSTRUCTOR;

    let employee = {
      id: this.state.id,
      firstName: values.firstName,
      lastName: values.lastName,
      rank: values.rank,
    };

    if (this.state.id === -1) {
      EmployeeDataService.createCourse(username, course).then(() =>
        this.props.history.push("/courses")
      );
    } else {
      EmployeeDataService.updateEmployee(
        username,
        this.state.id,
        course
      ).then(() => this.props.history.push("/courses"));
    }

    console.log(values);
  }

  render() {
    let { firstName, lastName, rank, id } = this.state;

    return (
      <div>
        <h3>Employee</h3>

        <div className="container">
          <Formik
            initialValues={{ id, firstName, lastName, rank }}
            onSubmit={this.onSubmit}
            validateOnChange={false}
            validateOnBlur={false}
            validate={this.validate}
            enableReinitialize={true}
          >
            {(props) => (
              <Form>
                <ErrorMessage
                  name="firstName"
                  component="div"
                  className="alert alert-warning"
                />

                <fieldset className="form-group">
                  <label>Id</label>
                  <Field
                    className="form-control"
                    type="text"
                    name="id"
                    disabled
                  />
                </fieldset>

                <fieldset className="form-group">
                  <label>First Name</label>
                  <Field
                    className="form-control"
                    type="text"
                    name="firstName"
                  />
                </fieldset>
                <fieldset className="form-group">
                  <label>Last Name</label>
                  <Field className="form-control" type="text" name="lastName" />
                </fieldset>
                <fieldset className="form-group">
                  <label>Rank</label>
                  <Field className="form-control" type="text" name="rank" />
                </fieldset>
                <button className="btn btn-success" type="submit">
                  Save
                </button>
              </Form>
            )}
          </Formik>
        </div>
      </div>
    );
  }
}

export default EmployeeComponent;
