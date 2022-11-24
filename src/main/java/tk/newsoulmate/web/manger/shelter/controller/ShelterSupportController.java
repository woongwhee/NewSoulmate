package tk.newsoulmate.web.manger.shelter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.ShelterSupportResponse;
import tk.newsoulmate.domain.vo.Transfer;
import tk.newsoulmate.web.shelter.service.ShelterService;
import tk.newsoulmate.web.support.service.SupportService;
import tk.newsoulmate.web.transfer.service.TransferService;

@WebServlet(name = "ShelterSupportController", value = "/shelter/supports")
public class ShelterSupportController extends HttpServlet {

	private final ShelterService shelterService;
	private final SupportService supportService;
	private final TransferService transferService;

	public ShelterSupportController() {
		this.shelterService = new ShelterService();
		this.supportService = new SupportService();
		this.transferService = new TransferService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object loginUser = request.getSession(false).getAttribute("loginUser");
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath());
		} else {
			Member member = (Member) loginUser;
			long shelterNo = member.getShelterNo();

			Shelter shelter = shelterService.find(shelterNo);
			List<ShelterSupportResponse> supports = supportService.findAllOnlyDoneByShelterNo(shelterNo);
			Transfer transfer = transferService.find(shelter.getTransferNo());

			request.setAttribute("latestTransfer", transfer);
			request.setAttribute("shelterName", shelter.getShelterName());
			request.setAttribute("supports", supports);
			request.getRequestDispatcher("/views/shelterManager/shelterSupport.jsp").forward(request, response);
		}
	}
}
