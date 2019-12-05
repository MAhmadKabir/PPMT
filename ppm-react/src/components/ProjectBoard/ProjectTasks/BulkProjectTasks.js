import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import classnames from "classnames";
import PropTypes from "prop-types";
import { addBulkProjectTask } from "../../../actions/backlogActions";
import { MDBDataTable } from "mdbreact";
import BulkAdd from "./BulkAdd";
import DataTable from "react-data-table-component";

class BulkProjectTasks extends Component {
  columns = [
    {
      name: "Project Sequence",
      selector: "projectSequence",
      sortable: true,
      width: 150
    },
    {
      name: "Summary",
      selector: "summary",
      sortable: true,
      width: 270
    },
    {
      name: "Acceptance Criteria",
      selector: "acceptanceCriteria",
      sortable: true,
      width: 200
    },
    {
      name: "Due Date",
      selector: "due_date",
      sortable: true,
      width: 100
    },
    {
      name: "Priority",
      selector: "priority",
      sortable: true,
      width: 150
    },
    {
      name: "Status",
      selector: "status",
      sortable: true,
      width: 100
    },
    {
      name: "Actions",
      selector: "edit"
    }
  ];
  state = {
    data: [
      {
        summary: "asd",
        acceptanceCriteria: "",
        status: "",
        priority: 0,
        due_date: "",
        projectIdentifier: "ASDS",
        projectSequence: 1
      },
      {
        summary: "asd",
        acceptanceCriteria: "",
        status: "",
        priority: 0,
        due_date: "",
        projectIdentifier: "ASDS",
        projectSequence: 2
      },
      {
        summary: "asd",
        acceptanceCriteria: "",
        status: "",
        priority: 0,
        due_date: "",
        projectIdentifier: "ASDS",
        projectSequence: 3
      }
    ],
    current: {}
  };
  onSubmit = model => {
    let data = [];
    let seq = 0;
    // alert(JSON.stringify(model));
    //  if (model.projectSequence) {
    //   alert("inside if" + model.projectSequence);
    //   data = this.state.data.filter(d => {
    //   seq += d.projectSequence;
    // alert("inside data filter" + d.projectSequence);
    //     return d.projectSequence != model.projectSequence;
    //   });
    // } else {
    // alert("inside else");
    // alert(JSON.stringify(data));
    // model.projectSequence += new Date();
    data = this.state.data.slice();
    // alert("inside else after slicing");
    // alert(JSON.stringify(data));
    //   model.projectSequence = "";
    //     }
    this.setState({
      current: {}, // todo
      data: [model, ...data]
    });
  };
  onbulkSubmit = () => {
    // e.preventDefault();
    // console.log(this.state.data);
    // alert("In on bulk submit method");
    // console.log("after bulk");
    // alert(this.props.history);
    this.props.addBulkProjectTask(
      this.props.match.params.id,
      this.state.data,
      this.props.history
    );
  };

  onNewClick = e => {
    alert("in on new click");
    this.setState({
      current: {}
    });
  };

  render() {
    let data = this.state.data.map(d => {
      return (
        <tr key={d.projectSequence}>
          <td>{d.acceptanceCriteria}</td>
          <td>{d.status}</td>
          <td>{d.summary}</td>
          <td>{d.priority}</td>
          <td>{d.projectSequence}</td>
          <td>{d.projectIdentifier}</td>
          <td>
            <button
              onClick={() => {
                this.onEdit(d.projectSequence);
              }}
            >
              edit
            </button>
          </td>
        </tr>
      );
    });
    return (
      <div className="App">
        <div className="form-actions">
          <button onClick={this.onNewClick} type="submit">
            NEW
          </button>
        </div>
        <BulkAdd
          key={this.state.current.projectSequence}
          id={this.props.match.params.id}
          className="form"
          title="Tasks"
          defaultValues={this.state.current}
          model={[
            {
              key: "summary",
              props: { required: true },
              placeholder: "Project Task Summary"
            },
            { key: "acceptanceCriteria", placeholder: "Acceptance Criteria" },
            {
              key: "due_date",
              label: "Due Date",
              type: "date"
            },
            {
              key: "priority",
              type: "select",
              value: "Select Priority",
              options: [
                { key: "none", label: "Select Priority", value: "0" },
                { key: "high", label: "High", value: "1" },
                { key: "medium", label: "Medium", value: "2" },
                { key: "low", label: "Low", value: "3" }
              ]
            },
            {
              key: "status",
              label: "Status",
              type: "checkbox",
              options: [
                { key: "todo", label: "TODO", value: "TODO" },
                {
                  key: "in_progress",
                  label: "IN PROGRESS",
                  value: "IN_PROGRESS"
                },
                { key: "done", label: "DONE", value: "DONE" }
              ]
            }
          ]}
          onSubmit={model => {
            this.onSubmit(model);
          }}
        />
        <DataTable title="uff" columns={this.columns} data={this.state.data} />
        <pre>{JSON.stringify(this.state.data)};</pre>
        <pre>{JSON.stringify(this.state.current)};</pre>
        <button onClick={() => this.onbulkSubmit()} type="submit">
          Add bulk
        </button>
      </div>
    );
  }
}
BulkProjectTasks.propTypes = {
  addBulkProjectTask: PropTypes.func.isRequired
  //   errors: PropTypes.object.isRequired
};
const mapStateToProps = state => ({
  //   errors: state.errors
});
export default connect(null, {
  addBulkProjectTask
})(BulkProjectTasks);
