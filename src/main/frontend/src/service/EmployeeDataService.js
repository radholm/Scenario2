import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/";
const employeeEnum = "EMPLOYEE";
const managerEnum = "MANAGER";
const ceoEnum = "CEO";

class EmployeeDataService {
  retrieveAllEmployees() {
    return axios.get(`${API_URL}/${employeeEnum}`);
  }

  addEmployee(employee) {
    //name, role, rank etc
    return axios.post(`${API_URL}/${employee}`);
  }

  addEmployee(employee, superiorId) {
    //name, role, rank etc
    return axios.post(`${API_URL}/${employeeEnum}/${superiorId}`, employee);
  }

  updateEmployee(employeeId) {
    return axios.put(`${API_URL}/${employeeId}`);
  }

  deleteEmployee(employeeId) {
    return axios.delete(`${API_URL}/${employeeId}`).then(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error.response.message);
      }
    );
  }
}

export default new EmployeeDataService();
