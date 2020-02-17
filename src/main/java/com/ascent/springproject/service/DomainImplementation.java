package com.ascent.springproject.service;


import org.springframework.stereotype.Service;

@Service

public class DomainImplementation implements Domain {


    public Long basicCtc(Long ctc, Long min_wage) {

        long basic;
//        Long min_wage_session = min_wage;

        ctc = (ctc * 30 / 100);

        if (ctc > min_wage) {
            basic = ((ctc) + ((ctc) - min_wage) * 12 / 100 * 76 / 100);

        } else {
            if (ctc > min_wage) {
                basic = Math.round(ctc);
            } else {
                basic = Math.round(min_wage);
            }


        }

        return basic;
    }


    public Long bonusCtc(Long basic) {

        return (Math.round(basic * 8.33 / 100));
    }


////            #########################################################################
    //                        Employer Contribution
////            #########################################################################


    //   Employer pf


    public Long employerPfContribution(Long basic) {

        return (long) Math.round(basic * 12 / 100);
    }

    //   Gratuity

    public Long gratuityFromCtc(Long basic) {
        return Math.round((basic * 4.81 / 100));
    }

    //    Gross

    public Long grossTotal(Long ctc, Long employer_pf, Long gratuity) {

        return (long) (int) Math.round((ctc - (employer_pf + gratuity)) / (1 + 0.0475));
    }


    //  Employer ESI


    public Long employerEsiContribution(Long gross) {
        return Math.round((gross * 4.75 / 100));
    }


    // ##########################################################################3


//           ####################################################################
    //                         Employee Contribution
//          #####################################################################


    // Employee pf

    public Long employeePfContribution(Long basic) {
        //int basic_got = basic;
        return (long) Math.round((basic * 12 / 100));
    }

    //Employee ESI


    public Long employeeEsiContribution(Long gross) {
        return Math.round((gross * 1.75 / 100));
    }

//             ########################################################################
//                                            NetPay
//            #########################################################################

    public Long netpay(Long gross, Long employee_pf, Long employee_esi) {

        return gross - employee_pf - employee_esi;

    }


////             ########################################################################
////                                            Home Rent Allowance                           // wait
////            #########################################################################


    public Long homeRentAllowance(Long basic, Long bonus_got, Long gross_ded, Long netpay_got, Long HRA_PERCENT) {
        long hra_inter_if;
        long hra;


//        Model m = new Model();
//        m.setSTATE(state);
//        m.branches();
        //       Long HRA_PERCENT = hra_per;


        long hra_calc = netpay_got + gross_ded - basic - bonus_got;

        if (hra_calc < 0) {
            hra_inter_if = 0L;
        } else {

            hra_inter_if = hra_calc;

        }

        long basic_mul_hra_percentage = basic * HRA_PERCENT / 100;

        if (hra_inter_if < basic_mul_hra_percentage) {

            hra = hra_calc;

        } else {
            hra = basic * HRA_PERCENT / 100;

        }
        return hra;
    }


////             ########################################################################
////                                            Gross deduction
////            #########################################################################


    public Long grossDed(Long employee_pf, Long employee_esi) {

        return employee_esi + employee_pf;
    }


////             ########################################################################
////                                            Net Take Home
////            #########################################################################


    public Long netTakeHome(Long gross, Long gross_ded) {

        return gross - gross_ded;
    }


////             ########################################################################
////                                            Difference
////            #########################################################################


    public Long difference(Long nettakehome, Long netpay) {

        return nettakehome - netpay;
    }

////             ########################################################################
////                                            Pt Gross
////            #########################################################################

    public Long ptGross(Long netpay, Long gross_ded) {
        return netpay + gross_ded;

    }


}
