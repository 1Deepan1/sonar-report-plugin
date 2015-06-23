<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:template match="/">

		<html>
			<head>
				<link rel="stylesheet"
					href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
				<script src="../../../js/jquery.min.js"></script>
				<script src="../../../js/RGraph.common.core.js"></script>
				<script src="../../../js/RGraph.common.tooltips.js"></script>
				<script src="../../../js/RGraph.common.dynamic.js"></script>
				<script src="../../../js/RGraph.pie.js"></script>
				<script
					src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


				<title>Pie Chart</title>

				<script language="javascript">
					$(document).ready(function ()
					{
					var t1 =
					<xsl:value-of
						select="//resource/msr[key/text()='blocker_violations']/frmt_val/text()" />
					;
					var t2 =
					<xsl:value-of
						select="//resource/msr[key/text()='critical_violations']/frmt_val/text()" />
					;
					var t3 =
					<xsl:value-of
						select="//resource/msr[key/text()='major_violations']/frmt_val/text()" />
					;
					var t4 =
					<xsl:value-of
						select="//resource/msr[key/text()='minor_violations']/frmt_val/text()" />
					;
					var t5 =
					<xsl:value-of
						select="//resource/msr[key/text()='info_violations']/frmt_val/text()" />;
					var data = [t1,t2,t3,t4,t5];
					var labels =
					['Blocker','Critical','Major','Minor','Info'];
          
           <![CDATA[for (var i=0; i<data.length; ++i) {
                labels[i] = labels[i] + ', ' + data[i];
            }]]>

					var pie = new RGraph.Pie({
					id: 'cvs',
					data: data,
					options: {
					labels:
					labels,
					tooltips: labels,
					colors:
					['#EC0033','#A0D300','#FFCD00','#00B869','#999999'],
					strokestyle:
					'white',
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
				<div class="container">
					<div class="jumbotron">
						<h2>Sonar-Report-Plugin : Main Dashboard</h2>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Panel title</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>Lines of code</th>
												<th>Files</th>
												<th>Functions</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='ncloc']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='files']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='functions']/frmt_val/text()" />
												</td>
											</tr>
										</tbody>
									</table>
									<table class="table">
										<thead>
											<tr>
												<th>Directories</th>
												<th>Lines</th>
												<th>Classes </th>
												<th>Statements </th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='directories']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='lines']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='classes']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='statements']/frmt_val/text()" />
												</td>
											</tr>
										</tbody>
									</table>
									<table class="table">
										<thead>

											<tr>
												<th>Accessors</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='accessors']/frmt_val/text()" />
												</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div><!-- /.col-sm-4 -->
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Panel title</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>Duplications</th>
												<th>Lines</th>
												<th>Blocks</th>
												<th>Files</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='duplicated_lines_density']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='duplicated_lines']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='duplicated_blocks']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='duplicated_files']/frmt_val/text()" />
												</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div><!-- /.col-sm-4 -->
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Panel title</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>Complexity</th>
												<th>/Function</th>
												<th>/Class</th>
												<th>/File</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='complexity']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='function_complexity']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='class_complexity']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='file_complexity']/frmt_val/text()" />
												</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div><!-- /.col-sm-4 -->
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Panel title</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>

												<th>Debt</th>
												<th>Issues</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='sqale_index']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='open_issues']/frmt_val/text()" />
												</td>

											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div><!-- /.col-sm-4 -->

						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Panel title</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>Unit test success</th>
												<th>failures</th>
												<th>Errors</th>
												<th>Tests</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='test_success_density']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='test_failures']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='test_errors']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='tests']/frmt_val/text()" />
												</td>

											</tr>
										</tbody>
									</table>
									<table class="table">
										<thead>
											<tr>

												<th>Execution Time</th>
												<th>skipped unit tests</th>
												<th>Coverage by unit tests</th>
											</tr>
										</thead>
										<tbody>
											<tr>

												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='test_execution_time']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='Skipped unit tests']/frmt_val/text()" />
												</td>
												<td>
													<xsl:value-of
														select="//resource/msr[key/text()='Coverage']/frmt_val/text()" />
												</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div><!-- /.col-sm-4 -->
					</div>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
