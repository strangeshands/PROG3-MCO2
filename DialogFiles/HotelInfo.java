package DialogFiles;
import ClassFiles.*;
import FormatFiles.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;


/**
 *  Displays the hotel information
 */
public class HotelInfo extends JDialog {
    /**
     * Varied fonts used in this class
     */
    private final Font fontSmallPlain = new Font("Century Gothic", Font.PLAIN, 12);
    private final Font fontSmallBold = new Font("Century Gothic", Font.BOLD, 12);
    private final Font fontLarge = new Font("Century Gothic", Font.BOLD, 15);

    /**
     * decimal format that shows two decimals for float.
     */
    private DecimalFormat df = new DecimalFormat("#.##");



    /**
     * Constructs a HotelCreatedInfo dialog with the given hotel and title.
     * 
     * @param hotel the hotel to show
     * @param title the title of the dialog
     */
    public HotelInfo(Hotel hotel, String title, int mode) {
        setModal(true);
        setLayout(new BorderLayout());
        setTitle(title);

        switch (mode) {
            case 1 -> printCreatedInfo(hotel);
            case 2 -> printDPMInfo(hotel);
            case 3 -> printVoucherInfo(hotel);
        }

        // Other properties
        setLocationRelativeTo(null);
        switch (mode) {
            case 1 -> {
                setSize(300, 200);
                setMaximumSize(new Dimension(300, 200));
            }
            case 2 -> {
                setSize(450, 350);
                setMaximumSize(new Dimension(450, 350));
            } 
            case 3 -> {
                setSize(450, 350);
                setMaximumSize(new Dimension(450, 350));
            }
        }
        setResizable(false);
        setVisible(true);
    }



    /**
     * Constructs a HotelCreatedInfo dialog with the given hotel and date
     * 
     * @param hotel the hotel to show
     * @param date the date to show
     */
    public HotelInfo(Hotel hotel, int date) {
        setModal(true);
        setLayout(new BorderLayout());
        setTitle("Hotel DATE Information");

        printDateInfo(hotel, date);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        add(closeButton, BorderLayout.SOUTH);

        // Other properties
        setLocationRelativeTo(null);
        setSize(400, 400);
        setMaximumSize(new Dimension(400, 400));
        setResizable(false);
        setVisible(true);
    }



    /**
     * Constructs a HotelCreatedInfo dialog with the given hotel and room
     * 
     * @param hotel the hotel to show
     * @param room the room to show
     */
    public HotelInfo(Hotel hotel, Room room) {
        setModal(true);
        setLayout(new BorderLayout());
        setTitle("Search by: ROOM");

        printRoomInfo(hotel, room);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        add(closeButton, BorderLayout.SOUTH);

        // Other properties
        setLocationRelativeTo(null);
        setSize(450, 400);
        setMaximumSize(new Dimension(450, 400));
        setResizable(false);
        setVisible(true);
    }



    /**
     * Constructs a HotelCreatedInfo dialog with the given hotel and reservation
     * 
     * @param hotel the hotel to show
     * @param reserv the reservation to show
     */
    public HotelInfo(Hotel hotel, Reservation reserv) {
        setModal(true);
        setLayout(new BorderLayout());
        setTitle("RESERVATION");

        printReservationInfo(hotel, reserv);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        add(closeButton, BorderLayout.SOUTH);

        // Other properties
        setLocationRelativeTo(null);
        setSize(300, 400);
        setMaximumSize(new Dimension(300, 400));
        setResizable(false);
        setVisible(true);
    }
    


    /**
     * Prints hotel creation information
     * 
     * @param hotel The hotel to show
     */
    private void printCreatedInfo(Hotel hotel) {
        JLabel hotelNameLabel = new JLabel("Hotel Name: " + hotel.getHotelName());
        hotelNameLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        add(hotelNameLabel, BorderLayout.NORTH);

        JTextArea roomListArea = new JTextArea();
        roomListArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        roomListArea.setEditable(false);

        for (int i = 0; i < hotel.getRoomList().size(); i++) {
            roomListArea.append("Room: " + hotel.getRoomList().get(i).getRoomNameType());

            if (i + 1 != hotel.getRoomList().size())
                roomListArea.append("\n");
        }

        add(new JScrollPane(roomListArea), BorderLayout.CENTER);
    }



    /**
     * Printd the DPM information
     * 
     * @param hotel The hotel to show
     */
    private void printDPMInfo(Hotel hotel) {
        // Display room information
        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel roomInfoTitle = new JLabel("DATE PRICE MODIFIER INFORMATION -- ");
        roomInfoTitle.setFont(fontLarge);
        centerPanel.add(roomInfoTitle, BorderLayout.NORTH);

        // Display days and status
        ArrayList<DPM> dpm = hotel.getDpmList();
        String[] columnNames = {"Date", "Rate", "Date", "Rate", "Date", "Rate"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < dpm.size(); i++) {
            if (i % 3 == 0) {
                model.addRow(new Object[6]);
            }
            int col = i % 3 * 2;
            model.setValueAt("June " + (i + 1), model.getRowCount() - 1, col);
            model.setValueAt(df.format((dpm.get(i).getRate() * 100)) + "%", model.getRowCount() - 1, col + 1);
        }

        JTable daysTable = new JTable(model);
        daysTable.setFont(fontSmallPlain);
        daysTable.setRowHeight(20);
        daysTable.setEnabled(false);

        JTableHeader tableHeader = daysTable.getTableHeader();
        tableHeader.setFont(fontLarge);

        JScrollPane scrollPane = new JScrollPane(daysTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a new panel to hold the room info and days table
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(contentPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.NORTH);
    }



    /**
     * Prints the room information
     * 
     * @param hotel The hotel to show
     * @param room The room to show
     */
    private void printRoomInfo(Hotel hotel, Room room) {
        // Display high level info
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        HighLevelInfo hlinfo = new HighLevelInfo(hotel);
        infoPanel.add(hlinfo, BorderLayout.CENTER);
        infoPanel.setPreferredSize(new Dimension(450, 100));
        add(infoPanel, BorderLayout.NORTH);

        // Display room information
        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel roomInfoTitle = new JLabel("ROOM INFORMATION -- ");
        roomInfoTitle.setFont(fontLarge);
        roomInfoTitle.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        centerPanel.add(roomInfoTitle, BorderLayout.NORTH);

        JPanel roomInfoPanel = new JPanel();
        roomInfoPanel.setLayout(new GridLayout(3, 1));
        JLabel roomName = new JLabel(">> ROOM: " + room.getRoomName());
        roomName.setFont(fontSmallPlain);
        roomInfoPanel.add(roomName);
        JLabel roomType = new JLabel(">> ROOM TYPE: " + room.getRoomType());
        roomType.setFont(fontSmallPlain);
        roomInfoPanel.add(roomType);
        JLabel roomPrice = new JLabel(">> ROOM PRICE: " + room.getRoomPrice());
        roomPrice.setFont(fontSmallPlain);
        roomInfoPanel.add(roomPrice);

        roomInfoPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 10));

        centerPanel.add(roomInfoPanel, BorderLayout.CENTER);

        // Display days and status
        ArrayList<Day> days = room.getDays();
        String[] columnNames = {"Date", "Status", "Date", "Status", "Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < days.size(); i++) {
            if (i % 3 == 0) {
                model.addRow(new Object[6]);
            }
            int col = i % 3 * 2;
            model.setValueAt("June " + days.get(i).getDate(), model.getRowCount() - 1, col);
            model.setValueAt(days.get(i).getStatus(), model.getRowCount() - 1, col + 1);
        }

        JTable daysTable = new JTable(model);
        daysTable.setFont(fontSmallPlain);
        daysTable.setRowHeight(20);
        daysTable.setEnabled(false);

        JTableHeader tableHeader = daysTable.getTableHeader();
        tableHeader.setFont(fontLarge);

        JScrollPane scrollPane = new JScrollPane(daysTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a new panel to hold the room info and days table
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(roomInfoPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(contentPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }



    /**
     * Printf reservation info
     * 
     * @param hotel The hotel to show
     * @param reserv The reservation to show
     */
    private void printReservationInfo(Hotel hotel, Reservation reserv) {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel welcome = new JLabel("Welcome, " + reserv.getReserveFName() + "!");
        welcome.setFont(fontLarge);
        northPanel.add(welcome);

        add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel regName = new JLabel("REGISTERED NAME: " + reserv.getReserveName());
        regName.setFont(fontSmallPlain);
        centerPanel.add(regName);

        JLabel status = new JLabel("STATUS: " + reserv.getStatus());
        status.setFont(fontSmallPlain);
        centerPanel.add(status);

        JLabel room = new JLabel("ROOM: " + reserv.getRoom().getRoomNameType());
        room.setFont(fontSmallPlain);
        centerPanel.add(room);

        JLabel checkIn = new JLabel("CHECK IN: June " + reserv.getCheckIn());
        checkIn.setFont(fontSmallPlain);
        centerPanel.add(checkIn);

        JLabel checkOut = new JLabel("CHECK OUT: June " + reserv.getCheckOut());
        checkOut.setFont(fontSmallPlain);
        centerPanel.add(checkOut);
        centerPanel.add(Box.createVerticalStrut(10));

        JLabel breakdown = new JLabel("PRICE BREAKDOWN:");
        breakdown.setFont(fontSmallBold);
        centerPanel.add(breakdown);

        JTextArea brkdownDetails = new JTextArea();
        brkdownDetails.setEditable(false);
        brkdownDetails.setFont(fontSmallPlain);
        
        String breakdownDetails =   ">> ROOM PRICE: " + reserv.getRoom().getRoomPrice() + "\n\n";                            

        Day reserveDay;
        int i = 0;
        for (int day = reserv.getCheckIn(); day <= reserv.getCheckOut(); day++) {
            reserveDay = reserv.getReserveDays().get(i);
            if (day < reserv.getCheckOut()) {
                breakdownDetails += "June " + day + " -- " + 
                                    df.format((reserv.getDPMRate(i) * 100))  + "% x " + 
                                    reserv.getRoom().getRoomPrice() + " = " + 

                                    df.format(reserveDay.getDPM().
                                    computeDPM(reserv.getRoom().getRoomPrice())) + "\n";
            }
            else {
                breakdownDetails += "June " + day + " -- " +
                                    "CHECKOUT\n";
            }
            
            i++;
        }
        breakdownDetails += "\n>> TOTAL PRICE: " + df.format(reserv.getOriginalPrice());
        breakdownDetails += "\n\n----------------------\n";

        if (reserv.getVoucher() != null) {
            breakdownDetails += "\nVOUCHER CODE: " + reserv.getVoucher().getDiscountCode();

            if (!(reserv.getVoucher() instanceof StayVoucher))
                breakdownDetails += "\nVOUCHER DISCOUNT: -" + 
                                    df.format(reserv.getVoucher().getDiscount(reserv.getOriginalPrice())) + 
                                    "\n";
            else {
                StayVoucher stayVoucher = (StayVoucher)reserv.getVoucher();
                breakdownDetails += "\nVOUCHER DISCOUNT: -" + 
                                    df.format(stayVoucher.getDiscount()) + 
                                    "\n";
            }

            breakdownDetails += "\n----------------------\n";
            breakdownDetails += "\n>> FINAL PRICE: " + df.format(reserv.getDiscountedPrice());
        } else {
            breakdownDetails += "\n>> FINAL PRICE: " + df.format(reserv.getDiscountedPrice());
        }

        brkdownDetails.setText(breakdownDetails);
        brkdownDetails.setLineWrap(true);
        brkdownDetails.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(brkdownDetails);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);
    }



    /**
     * Prints date information
     * 
     * @param hotel The hotel to show
     * @param date The date selected
     */
    private void printDateInfo(Hotel hotel, int date) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        HighLevelInfo hlinfo = new HighLevelInfo(hotel);
        infoPanel.add(hlinfo);
        infoPanel.setPreferredSize(new Dimension(450, 100));
        add(infoPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel availLabel = new JLabel("AVAILABILITY FOR JUNE " + date);
        availLabel.setFont(fontLarge);
        centerPanel.add(availLabel);

        ArrayList<Room> freeRooms = hotel.getFreeRooms(date);
        if (!freeRooms.isEmpty()) {
            JLabel availRooms = new JLabel("AVAILABLE ROOMS");
            availRooms.setFont(fontSmallBold);
            centerPanel.add(availRooms);

            JTextArea freeRoomArea = new JTextArea();
            freeRoomArea.setFont(fontSmallPlain);
            freeRoomArea.setEditable(false);
            for (Room room : freeRooms) {
                freeRoomArea.append(">> Room: " + room.getRoomNameType() + "\n");
            }
            centerPanel.add(new JScrollPane(freeRoomArea));
        }

        ArrayList<Room> bookedRooms = hotel.getBookedRooms(date);
        if (!bookedRooms.isEmpty()) {
            JLabel bookRoomLabel = new JLabel("BOOKED ROOMS");
            bookRoomLabel.setFont(fontSmallBold);
            centerPanel.add(bookRoomLabel);

            JTextArea bookedRoomArea = new JTextArea();
            bookedRoomArea.setFont(fontSmallPlain);
            bookedRoomArea.setEditable(false);
            for (Room room : bookedRooms) {
                bookedRoomArea.append(">> Room: " + room.getRoomNameType() + "\n");
            }
            centerPanel.add(new JScrollPane(bookedRoomArea));
        }

        add(centerPanel, BorderLayout.CENTER);
    }



    /**
     * Prints the voucher list and information
     * 
     * @param hotel The hotel to search
     */
    private void printVoucherInfo(Hotel hotel) {
        JTable voucherTable;

        ArrayList<Voucher> voucherList = hotel.getVoucherList();

        setLayout(new BorderLayout());

        // Create a JTable to display voucher information
        String[] columnNames = {"Voucher Code", "Type", "Discount Rate", "Condition"};
        Object[][] data = new Object[voucherList.size()][4];
        for (int i = 0; i < voucherList.size(); i++) {
            Voucher voucher = voucherList.get(i);
            data[i][0] = voucher.getDiscountCode();
            data[i][1] = voucher.getType();
            data[i][2] = df.format((voucher.getDiscountMultiplier() * 100)) + "%";
            data[i][3] = voucher.getCondition();
        }

        voucherTable = new JTable(data, columnNames);
        voucherTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        voucherTable.setFillsViewportHeight(true);

        // Add the table to a JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(voucherTable);

        // Add the scroll pane to the panel
        add(tableScrollPane, BorderLayout.CENTER);
    }
}
