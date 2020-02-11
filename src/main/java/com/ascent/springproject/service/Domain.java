package com.ascent.springproject.service;

public interface Domain {

    Long basic_ctc(Long ctc,Long min_wage);
    Long bonus_ctc(Long basic);
    Long employer_pf_contribution(Long basic);
    Long gratuity_from_ctc(Long basic);
    Long gross_total(Long ctc , Long employer_pf , Long gratuity);
    Long employer_esi_contribution(Long gross);
    Long employee_pf_contribution(Long basic);
    Long employee_esi_contribution(Long gross);
    Long netpay(Long gross,Long employee_pf,Long employee_esi);
    Long homerentallowance(Long basic,Long bonus_got,Long gross_ded,Long netpay_got,Long HRA_PERCENT);
    Long grossDed(Long employee_pf,Long employee_esi);
    Long nettakehome(Long gross , Long gross_ded);
    Long difference(Long nettakehome,Long netpay);
    Long ptgross(Long netpay,Long gross_ded);
}
