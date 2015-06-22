<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match="/">
      <html>
		<head>
<script	src="../../../js/jquery.min.js"></script>
<script src="../../../js/RGraph.common.core.js"></script>
<script src="../../../js/RGraph.common.tooltips.js"></script>
<script src="../../../js/RGraph.common.dynamic.js"></script>
<script src="../../../js/RGraph.pie.js"></script>


<title>Pie Chart</title>

<script language="javascript">
        $(document).ready(function ()
        {
        	var t1 = <xsl:value-of select="//resource/msr[key/text()='blocker_violations']/frmt_val/text()" />;
            var t2 = <xsl:value-of select="//resource/msr[key/text()='critical_violations']/frmt_val/text()"/>;
            var t3 = <xsl:value-of select="//resource/msr[key/text()='major_violations']/frmt_val/text()" />;
            var t4 = <xsl:value-of select="//resource/msr[key/text()='minor_violations']/frmt_val/text()" />;
            var t5 = <xsl:value-of select="//resource/msr[key/text()='info_violations']/frmt_val/text()" />;
            var data     = [t1,t2,t3,t4,t5];
            var labels   = ['Blocker','Critical','Major','Minor','Info'];
          
           <![CDATA[for (var i=0; i<data.length; ++i) {
                labels[i] = labels[i] + ', ' + data[i];
            }]]>

            var pie = new RGraph.Pie({
                id: 'cvs',
                data: data,
                options: {
                    labels: labels,
                    tooltips: labels,
                    colors: ['#EC0033','#A0D300','#FFCD00','#00B869','#999999'],
                    strokestyle: 'white',
                    linewidth: 0,
                    shadow: {
                        offsetx: 2,
                        offsety: 2,
                        blur: 3
                    },
                    exploded: 8
                }
            }).draw()
        })
    </script>
</head>
         <body>
            <h1>Main Dashboard</h1>
             <table border="0" cellpadding="5" cellspacing="5">
           		 <tr>
  					<td colspan="5">Lines of code</td>
 					<td colspan="2">Files</td>
  					<td colspan="3">Functions</td>
		 		</tr>
		 		 <tr>
  					<td colspan="5"><xsl:value-of select="//resource/msr[key/text()='ncloc']/frmt_val/text()" /></td>
 					<td colspan="2"><xsl:value-of select="//resource/msr[key/text()='files']/frmt_val/text()" /></td>
  					<td colspan="3"><xsl:value-of select="//resource/msr[key/text()='functions']/frmt_val/text()" /></td>
		 		</tr>
		 	</table>
		 	
		 	<table border="0" cellpadding="5" cellspacing="5">
		 		<tr>
  					<td colspan="2" rowspan="2">Java</td> 
  					<td>Directories</td> 
  					<td>Lines</td>
  					<td>Classes</td> 
  					<td>Statements</td> 
  					<td>Accessors</td> 					
		 		</tr>
		 		<tr>
		 			<td><xsl:value-of select="//resource/msr[key/text()='directories']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='lines']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='classes']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='statements']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='accessors']/frmt_val/text()" /></td> 
		 		</tr>
             </table>
             
             <table border="0" cellpadding="5" cellspacing="5">
		 		<tr>
  					<td>Duplications</td> 
  					<td>Lines</td>
  					<td>Blocks</td> 
  					<td>Files</td> 
		 		</tr>
		 		<tr>
  					<td><xsl:value-of select="//resource/msr[key/text()='duplicated_lines_density']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='duplicated_lines']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='duplicated_blocks']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='duplicated_files']/frmt_val/text()" /></td> 
		 		</tr>
             </table>
             
             <table border="0" cellpadding="5" cellspacing="5">
		 		<tr>
  					<td>Complexity</td> 
  					<td>/Function</td>
  					<td>/Class</td> 
  					<td>/File</td> 
		 		</tr>
		 		<tr>
  					<td><xsl:value-of select="//resource/msr[key/text()='complexity']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='function_complexity']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='class_complexity']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='file_complexity']/frmt_val/text()" /></td> 
		 		</tr>
             </table>
             
              <table border="0" cellpadding="5" cellspacing="5">
		 		<tr>
		 			<td rowspan="2"><canvas id="cvs" width="600" height="250" style="border: 1px solid #ccc"></canvas></td>
  					<td>Debt</td> 
  					<td>Issues</td>
		 		</tr>
		 		<tr>
  					<td><xsl:value-of select="//resource/msr[key/text()='sqale_index']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='open_issues']/frmt_val/text()" /></td> 
  					
		 		</tr>
             </table>
             
             <table border="0" cellpadding="5" cellspacing="5">
		 		<tr>
  					<td>Unit test success</td> 
  					<td>failures</td>
  					<td>Errors</td> 
  					<td>Tests</td>
  					<td>Execution Time</td> 
  					<td>skipped unit tests</td> 
  					<td>Coverage by unit tests</td> 
		 		</tr>
		 		<tr>
  					<td><xsl:value-of select="//resource/msr[key/text()='test_success_density']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='test_failures']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='test_errors']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='tests']/frmt_val/text()" /></td> 
  					<td><xsl:value-of select="//resource/msr[key/text()='test_execution_time']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='Skipped unit tests']/frmt_val/text()" /></td>
  					<td><xsl:value-of select="//resource/msr[key/text()='Coverage']/frmt_val/text()" /></td>
		 		</tr>
             </table>
             
         </body>
      </html>
   </xsl:template>
</xsl:stylesheet>
