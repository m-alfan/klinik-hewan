import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import java.awt.Color;
import java.math.BigDecimal;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

public class Reports {

    public Reports() {
      build();
    }

    private void build() {    
      StyleBuilder boldStyle         = stl.style().bold();
      StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
      StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
                                           .setBorder(stl.pen1Point())
                                           .setBackgroundColor(Color.LIGHT_GRAY);

      StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
                                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                          .setFontSize(15);
              
       //                                                      title,     field name     data type
      TextColumnBuilder<String> j_hewan       = col.column("Jenis Hewan", "j_hewan", type.stringType()).setStyle(boldStyle);
      TextColumnBuilder<String> nama          = col.column("Nama", "nama", type.stringType());
      TextColumnBuilder<String> check_in      = col.column("Check In", "check_in", type.stringType());
      TextColumnBuilder<String> check_out     = col.column("Check Out", "check_out", type.stringType());
      TextColumnBuilder<BigDecimal> harga     = col.column("Harga", "harga", type.bigDecimalType());
      TextColumnBuilder<String> ras           = col.column("Ras", "ras", type.stringType());
      TextColumnBuilder<String> ket           = col.column("Keterangan", "ket", type.stringType());
      TextColumnBuilder<String> status        = col.column("Status", "status", type.stringType());
      TextColumnBuilder<String> jk            = col.column("Jenis Kelamain", "jk", type.stringType());
      TextColumnBuilder<String> no_tlp        = col.column("No Telp", "no_tlp", type.stringType());
      TextColumnBuilder<String> alamat        = col.column("Status", "alamat", type.stringType());
      TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn("No.")
                                                           //sets the fixed width of a column, width = 2 * character width
                                                          .setFixedColumns(2)
                                                          .setHorizontalAlignment(HorizontalAlignment.CENTER);
      try {
        Koneksidatabase db = new Koneksidatabase();

        report()//create new report design
          .setColumnTitleStyle(columnTitleStyle)
          .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
          .highlightDetailEvenRows()
          .columns(//add columns
          rowNumberColumn,j_hewan,nama,check_in,check_out,harga,ras,ket,status,jk,no_tlp,alamat)
          .groupBy(j_hewan)
          .title(
            cmp.horizontalList()
            .add(
              cmp.image(Templates.class.getResource("image/report.png")).setFixedDimension(80, 80),
              cmp.text("Reports").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
              cmp.text("Transactions").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
            .newRow()
            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
          )//shows report title
          .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
          .setDataSource("SELECT t.j_hewan, c.nama, t.check_in, t.check_out, t.harga, t.ras, t.ket, CASE WHEN t.status = 'p' THEN 'Proses' WHEN t.status = 'f' THEN 'Finish' ELSE 'Cancel' END as status, IF(c.jk = 'p', 'Perempuan', 'Laki-laki') as jk, c.no_tlp, c.alamat FROM transactions as t, customers as c WHERE t.id = c.id ORDER BY j_hewan",db.koneksi)//set datasource
          .show(false);//create and show report
           //.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
      } catch (DRException e) {
        e.printStackTrace();
      }
    }
         
    public static void main(String[] args) {
       new Reports();
    }
}