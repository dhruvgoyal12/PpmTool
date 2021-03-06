import React, { Component } from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createProject} from "../../actions/projectActions";

class AddProject extends Component {
    constructor(){
        super();

        this.state={
            "projectName": "",
            "projectIdentifier": "",
            "description": "",
            "startDate": "",
            "endDate": ""
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    onChange(e){
        // this.setState({projectName: e.target.value})
         this.setState({[e.target.name]: e.target.value})

    }
    //Event parameter
    onSubmit(e){

        // To prevent the form from reloading on submit
        e.preventDefault();

        // Submitting new project object
        const newProject = {
            "projectName": this.state.projectName,
            "projectIdentifier": this.state.projectIdentifier,
            "description": this.state.description,
            "startDate": this.state.startDate,
            "endDate": this.state.endDate
        }

        //console.log(newProject);
        this.props.createProject(newProject, this.props.history);
    }

    render() {
        return (
            //Check name attribute input fiels
            <div className="project">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Create / Edit Project form</h5>
                    <hr />
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className="form-control form-control-lg " placeholder="Project Name" name="projectName" value={this.state.projectName} onChange={this.onChange}
                            />
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control form-control-lg" placeholder="Unique Project ID" name="projectIdentifier" value={this.state.projectIdentifier} onChange={this.onChange}/>
                        </div>
   
                        <div className="form-group">
                            <textarea className="form-control form-control-lg" placeholder="Project Description" name="description" value={this.state.description} onChange={this.onChange}></textarea>
                        </div>
                        <h6>Start Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="startDate" value={this.state.startDate} onChange={this.onChange}/>
                        </div>
                        <h6>Estimated End Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="endDate" value={this.state.endDate} onChange={this.onChange}/>
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
        )
    }
}
AddProject.PropTypes = {
    createProject :  PropTypes.func.isRequired
}
export default connect(null, {createProject})(AddProject);