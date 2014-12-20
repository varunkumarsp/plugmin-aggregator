<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ page import="java.util.*,org.istage.service.b2b.PrintTicketForm" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 
 PrintTicketForm ticketForm = (PrintTicketForm) request.getAttribute("orderDetails");
 
 request.setAttribute("tickForm", ticketForm);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>IndianStage - ETicket</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
.rules {
	font-size : 14px;
	font-family:arial;
}
.rules ul {
    margin: 0;
	margin-right: 5px;
	margin-left: 5px;
	margin-bottom: 10px;
	padding: 0;
	list-style-type: none;
	font-size: 10px;	
	
}
td.box{		
	vertical-align: middle;
	padding: 5px;
	font-size:12px;
	font-family:Arial;	
	width: 48%;
}
td.vtop{
	vertical-align: middle;
	font-size:12px;
	font-family:Arial;	
	height: 50px;
}
</style>
<body onLoad="print();">
<div id="container" style="width:620px;margin:0px auto;padding:0;position: relative">
	<img src="/resources/images/old/left.png" style="position: absolute;top: 195px;left:0px;">
	<img src="/resources/images/old/right.png" style="position: absolute;top: 195px;right:-6px;">
	<table cellspacing="0" cellpadding="10" border="0" width="620" style="font-size:12px;background-color:rgb(255,255,255);font-family:Arial;border-collapse: inherit;  border-color: #AFAFAF;  border-image: none;   border-style: solid;   border-width: 3px 3px;">
            <tbody><tr>
              <td width="620">
                <table cellspacing="0" cellpadding="0" border="0" width="600" style="border-collapse:inherit;font-size:12px;font-family:Arial">
                  <tbody><tr>
                    <td width="600">
                      <table cellspacing="0" cellpadding="0" border="0" style="font-size:12px;width:600px;background-color:rgb(255,255,255);font-family:Arial,Helvetica,sans-serif">
                        <tbody><tr>
                          <td style="width:348px" valign='middle'>
                            <img height="61px" style="margin:0;padding:0;width:177px" src="/resources/images/old/istage_logo.jpg">
                          </td>
                          <td style="width:252px;text-align:right;float:right" valign='middle'>
                            <img height="46px" style="margin:0;padding:0;width:252px;float:right" src="/resources/images/old/reach-us.jpg">
                            <br>
                            <span style="font-size:12px">Exchange this at the venue box office for ticket.</span>
                          </td>
                        </tr>
                        
                        <tr>
                          <td colspan="2">
						  <hr style="margin:0;padding:0;">
						  <p style="margin:4px;padding:4px;">
										Thank you for ordering tickets!
									</p>
                            <table cellspacing="0" cellpadding="0" style="width:600px;padding:0px;">
                              <tbody><tr>
                                <td height="10" colspan="3">
                              </td></tr>
                              <tr>
                                <td style="width:273px;font-size:12px">									
                                  <table cellspacing="0" cellpadding="0" style="font-size:12px">
                                    <tbody>									
									<tr>
                                      <td width="28">&nbsp;</td>
                                      <td align="left" style="width:230px;padding:3px 5px;border:2px solid #757575;font-size:12px">
                                        <span style="font-size:12px;font-family:Arial">ORDER ID &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:&nbsp</span>
                                        <span style="font-size:12px;font-family:Arial;font-weight:bold">${tickForm.orderNumber }</span>
										<div style="font-size:12px;font-family:Arial">
											Order Date &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:&nbsp ${tickForm.orderDate }											
										</div>
										<div style="font-size:12px;font-family:Arial">
											Payment Mode:&nbsp ${tickForm.paymentMethod }
										</div>
                                      </td>
                                    </tr>
                                  </tbody></table>
                                </td>
                                <td style="width:250px">
                                  <img width="250px" height="42px" style="float:right" src="http://in.bookmyshow.com/common/phpbarcode/image.php?code=code128&amp;o=2&amp;t=30&amp;r=1&amp;text=FNBG0002198615&amp;f=0&amp;a1=B&amp;a2=" alt="">
                                </td>
                                <td width="28">&nbsp;</td>
                              </tr>
                              <tr>
                                <td height="10" colspan="3">
                              </td></tr>
                            </tbody></table>
                          </td>
                        </tr>
                      </tbody></table>
                    </td>
                  </tr>
                  <tr>
				    <td valign="top" height="135px" style="padding:0px;width:600px;vertical-align:top" colspan="2">						
                      <table cellspacing="0" cellpadding="0" border="0" width="600">
                        <tbody><tr><td valign="top" height="100%" style="width:26px;margin:0;padding:0">
                          
                        </td>
                        <td valign="top" height="135px" style="width:548px;overflow:hidden">
                          <table cellspacing="0" cellpadding="0" border="0" width="548px">
                            <tbody><tr>
                              <td valign="top" height="115" style="width:548px;padding-top:10px;padding-bottom:10px">
								<table cellspacing="0" cellpadding="10" width="540" style="margin-left: 4px; margin-bottom: 8px; border:2px solid #757575">
									<tr>
										<td valign="middle" align="left" style="font-weight: bold;">											
											Customer Name: ${tickForm.customerName }
										</td>
									</tr>									
								</table>
                                <table cellspacing="0" cellpadding="0" border="1" width="540" style="margin-left: 5px;">
                                  <tr>
									<td class="box">
										<table>
											<tr>
												<td class="vtop"><img align="left" height="20px" style="width:19px;margin:0px" src="/resources/images/old/etitle.png"></td>
												<td class="vtop">${tickForm.eventName }</td>
											</tr>
										</table>														
									</td>
									<td class="box">
										<table>
											<tr>
												<td class="vtop"><img align="left"  style="width:20px;margin:0px" src="/resources/images/old/eseat.png"></td>
												<td class="vtop">Admit - <b>${tickForm.ticketQty } [ ${tickForm.ticketClass } ]</b></td>
											</tr>
											<c:if test="${tickForm.seatNumbers ne 'NA'}">
   												<tr>
												<td></td>
												<td style="font-size: 12px; font-family: aerial; font-weight: bold;">[ Seats - ${tickForm.seatNumbers } ]</td>
											</tr>
											</c:if>											
										</table>														
									</td>	
								  </tr>
								  <tr>
									<td class="box">
										<table>
											<tr>
												<td class="vtop"><img align="left" height="19px" style="width:17px;margin:0px" src="/resources/images/old/evenue.png"></td>
												<td class="vtop">${tickForm.venueAddress }</td>
											</tr>
											<c:if test="${tickForm.ageGuidance ne 'NA'}">
   												<tr>
													<td></td>
													<td><span style="font-size: 12px; font-family: aerial; font-weight: bold;">[ age guidance: above 12 years ]</span></td>
												</tr>
											</c:if>
										</table>														
									</td>
									<td class="box">
										<table>
											<tr>
												<td class="vtop">
													<table>
														<tr>
															<td class="vtop"><img align="left" height="19px" style="width:23px;margin:0px" src="/resources/images/old/edate.png"></td>
															<td class="vtop">${tickForm.showDt }</td>
														</tr>
													</table>																												
												</td>
												<td class="vtop">													
													<table>
														<tr>
															<td class="vtop"><img align="left" height="19px" src="/resources/images/old/etime.png" style="width:19px;margin:0px">		</td>
															<td class="vtop">${tickForm.showTm }</td>
														</tr>
													</table>													
												</td>	
											</tr>
										</table>											
									</td>	
								  </tr>
							  </table>
                              </td>
                            </tr>
                          </tbody></table>
                        </td>
                        <td valign="top" height="100%" style="width:26px;margin:0;padding:0">
                          
                        </td>
                      </tr></tbody></table>
                    </td>
                  </tr>
                       
                  
                  <tr>
                    <td height="10" style="width:600px" colspan="2">
                  </td></tr>
                  
                  <tr>
                    <td colspan="2">
					<hr style="">
					<p style="padding:0;padding-bottom: 5px; margin:0;">
						<u>Important Terms & Conditions:</u>
					</p>
					<!-- Rules and Regulations -->
                      <table cellspacing="0" cellpadding="0" style="width:600px;font-size:11px;background-color:#FAF9F9;">
                        <tbody>
							<tr>
<td width="50%">
	<table border="0" cellpadding="0" cellspacing="0" class="rules">
              <tr>
                <td><ul>
                  <li>E-ticket must be exchanged for physical tickets at the venue.</li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>Valid govt issued ID required for e-ticket exchange. </li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>Once redeemed, copies of e-ticket will not be permitted - No exceptions, please. </li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>No cancellations/refund/resale/upgrade/downgrade of tickets under any circumstances.</li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>No Photography and Videography in any form. </li>
                </ul></td>
              </tr>
              
              <tr>
                <td><ul>
                  <li>Organizer reserves right to frisk the ticket holders.</li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>Organizer does not take responsibility for loss or theft of personal belongings. </li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>The event is subject to Force majeure conditions. </li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>Parking near or at the event premises is at the risk of the vehicle owner.</li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>All rules of the auditorium/Venue will be applicable.</li>
                </ul></td>
              </tr>
            </table>
</td>
<td width="50%">
	<table border="0" cellspacing="0" cellpadding="0" class="rules">
              <tr>
                <td><ul>
                  <li>Ticket Holder has to be present for e-ticket exchange - No exceptions, please. </li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>Kindly retain physical tickets in a safe place; loss of the same will not warrant a replacement. <br />
                  </li>
                </ul></td>
              </tr>
              
              <tr>
                <td><ul>
                  <li>IndianStage will not be responsible for loss of physical tickets.</li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>No contrabanned substances permitted into the venue. </li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>No weapons of any sort will be tolerated. <br />
                  </li>
                </ul></td>
              </tr>
              
              <tr>
                <td><ul>
                  <li>Rights of admission are reserved with organizer.</li>
                </ul></td>
              </tr>
              
              <tr>
                <td><ul>
                  <li>Seats on first come first served basis after the gate opens (unless seat numbers mentioned). <br />
                  </li>
                </ul></td>
              </tr>
              
              <tr>
                <td><ul>
                  <li>Auditorium doors close at the time mentioned in the E-Ticket. Late comers will NOT be permitted to enter the auditorium.</li>
                </ul></td>
              </tr>
              <tr>
                <td><ul>
                  <li>Use of mobile phones and pagers is prohibited inside the auditorium/Venue. </li>
                </ul></td>
              </tr>
            </table>
</td>
</tr>

						</tbody>
					  </table>
                    </td>
                  </tr>
                  
                  
             <!-- Advertisement Block -->     
                  <tr>
                    <td style="width:600px;margin-top:3px">
                      <div id="advert" style="width:596px;float:left;">
                        
                      </div>
                    </td>
                  </tr>
                </tbody></table>
              </td>
            </tr>
          </tbody>
	</table>
</div>
</body>
</html>


