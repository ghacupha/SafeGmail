package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import util.Util;

import dom.MessageReviveRequestDO;

/**
 * @author Avi
 * 
 */
public final class JdbcReviveMessageRequestDaoImpl implements
		ReviveMessageRequestDao {

	public void createNewRequest(MessageReviveRequestDO messageReviveRequest) {
		// check if user exists in database
		String query = "INSERT INTO MessageRevivalRequest (requestorName, requestorEmail, senderEmail, requestReason, messageId, requestedOn) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, messageReviveRequest.getRequestorName());
			ps.setString(2, messageReviveRequest.getRequestorEmail());
			ps.setString(3, messageReviveRequest.getSenderEmail());
			ps.setString(4, messageReviveRequest.getRequestReason());
			ps.setString(5, messageReviveRequest.getMessageId());
			ps.setTimestamp(6, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}