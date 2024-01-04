package com.example.networkmeup.view.SearchJobs;

import com.example.networkmeup.dao.EmployeeDAO;
import com.example.networkmeup.dao.EmployerDAO;
import com.example.networkmeup.daoMemory.EmployeeDAOMemory;
import com.example.networkmeup.daoMemory.EmployerDAOMemory;
import com.example.networkmeup.domain.Application;
import com.example.networkmeup.domain.Availability;
import com.example.networkmeup.domain.Email;
import com.example.networkmeup.domain.Employee;
import com.example.networkmeup.domain.Employer;
import com.example.networkmeup.domain.Job;

import java.util.ArrayList;

public class SearchJobsPresenter {
    private SearchJobsView view;
    private String userToken;

    public SearchJobsPresenter(SearchJobsView view, String userToken) {
        this.view = view;
        this.userToken = userToken;
    }

    public ArrayList<Job> onCreate(){
        //create arraylist to return
        ArrayList<Job> matchingJobs = new ArrayList<>();

        //obtain current employee's data
        EmployeeDAO employeeDAO = new EmployeeDAOMemory();
        Employee currEmployee = employeeDAO.getByEmail(new Email(userToken));

        //obtain all employers to access all available and temporarily not available jobs
        EmployerDAO employerDAO = new EmployerDAOMemory();

        //check through all available and temporarily unavailable jobs and find matches
        for(Employer employer : employerDAO.getAll()){
            for(Job job : employer.getJobs()){
                if(job.acceptCV(currEmployee.getCV()) && (job.getAvailability().equals(Availability.Available) || job.getAvailability().equals(Availability.Temporarily_Unavailable))){
                    matchingJobs.add(job);
                }
            }
        }
        //remove jobs that the user has already applied to
        //loop through all job's applications and look for the same applicant
        for(Job job : matchingJobs){
            for(Application application : job.getApplications()){
                if(application.getEmployee().equals(currEmployee)){
                    matchingJobs.remove(job);
                    break;
                }
            }
        }

        //no matching jobs found, send message to view
        if(matchingJobs.size() == 0){
            view.noJobsFound("Unfortunately there are no jobs that match with your CV. Please try again later.", userToken);
        }

        return matchingJobs;
    }

    public void onItemClick(Job selectedJob) {
        view.showJobDetails(userToken, selectedJob);
    }
}
